package nav.cl.test_item_perzonalido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClienteAdapter extends BaseAdapter{
    private Context context;
    private List<Cliente> clientes;

    public ClienteAdapter(Context context, List<Cliente> clientes) {
        this.context = context;
        this.clientes = clientes;
    }


    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int i) {
        return clientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // acá tenemos que cargar o "inflate" --> cargar xml java
        // cliente item

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_cliente_item, null);
        }

        /*Obtengo el cliente en la posición determinada*/
        Cliente c = clientes.get(i);

        /*Rescato los TextView del XML*/
        TextView nombre = (TextView) view.findViewById(R.id.txtNombre);
        TextView edad = (TextView) view.findViewById(R.id.txtEdad);
        TextView ciudad = (TextView) view.findViewById(R.id.txtCiudad);

        /*Coloco los datos del objeto en el XML*/
        nombre.setText(c.getNombre());
        edad.setText(c.getEdad()+" años");
        ciudad.setText(c.getCiudad());

        if(c.getEdad() < 18){

        }

        return view;
    }
}
