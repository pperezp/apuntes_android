package nav.cl.testnavigationdrawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listar, container, false);
        ListView lvClientes = (ListView)v.findViewById(R.id.listaNombres);

        Cliente c1 = new Cliente("Nombre 1",10,"Ciudad 1");
        Cliente c2 = new Cliente("Nombre 2",20,"Ciudad 2");
        Cliente c3 = new Cliente("Nombre 3",30,"Ciudad 3");

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);
        clientes.add(c3);

        lvClientes.setAdapter(new ClienteAdapter(v.getContext(), clientes));

        return v;
    }

}
