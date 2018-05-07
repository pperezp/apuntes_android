package nav.cl.test_item_perzonalido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvClientes = (ListView)findViewById(R.id.lvClientes);


        /*Actual BD*/
        List<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente("Alvaro", 26, "Doñihue"));
        clientes.add(new Cliente("Damián Dendé", 24, "Namekusei"));
        clientes.add(new Cliente("Johnny", 34, "Rancagua"));
        clientes.add(new Cliente("Herrera", 42, "Maruri 69"));
        clientes.add(new Cliente("Bryan", 21, "Rancagua"));
        clientes.add(new Cliente("Dabura", 21, "Rancagua Sur"));
        /*Actual BD*/

        ClienteAdapter adapter = new ClienteAdapter(MainActivity.this, clientes);
        lvClientes.setAdapter(adapter);
    }
}
