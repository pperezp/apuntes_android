## Pasos Navigation Drawer

- Ir a res/layout/content_main.xml
	- android:id="@+id/contenido"
- Ir a res/menu/activity_main_drawer.xml
	- Acá creamos algunas opciones, como por ej: nav_crear
- Ahora creamos los fragments necesarios y modificamos los fragments xml. Para este ejemplo es necesario crear un fragment llamado ListarFragment
	- Además en las clases fragment, solo debe dejar el método onCreateView()
- Abrir MainActivity
- onNavigationItemSelected(MenuItem item)
	- Añadir FragmentManager fm = getSupportFragmentManager();
	- Generar un switch case según corresponda (Si se crearón 2 navs, se deben 		generar dos cases.)
	- En cada case:
	```
	Fragmento f = new Fragmento();
	fm.beginTransaction().replace(R.id.contenido, cf).commit();
	```
## Procesar un componente (botón) en un fragment
- Supongamos que el botón se llame btnGuardar
- En el método de onCreateView() del fragment:
```
View v = inflater.inflate(R.layout.fragment_crear, container, false);
final Context c = v.getContext();
btnGuardar.setOnClickListener(new View.OnClickListener() {
	@Override
		public void onClick(View view) {
			Toast.makeText(c, "Botón click!", Toast.LENGTH_SHORT).show();
		}
	});
return v;
```
## res/layout/nav_header_main.xml
- Es aquí donde se cambia el icono de la barra lateral y los datos
- Además es acá en donde se puede cambiar el fondo, por defecto esta android:background="@drawable/side_nav_bar"
- Si quieres cambiar el fondo por una foto cualquiera, la copias desde el navegador de archivos del sistema operativo, y le pegas en res/drawable. Renombras la foto (Por ejemplo fondo) y cambias la propiedad android:background="@drawable/fondo" en res/layout/nav_header_main.xml

## res/drawable/side_nav_bar.xml
- Es aquí donde se cambia el color de fondo de la barra lateral
	
## Pasos para un item personalizado en una lista
- Vamos a crear una clase Cliente con nombre, edad y ciudad (Generar constructor, getter y setter)
- En res/layout crear un nuevo EmptyActivity. Le llamamos ClienteItem
	- En él, diseñamos un item de un cliente (Por ejemplo 3 TextView hacia abajo)
	- Click secundario en los TextView --> Organize --> Expand Horizontally
	- En mi caso, coloque 3 (txtNombre, txtEdad, txtCiudad)
- Ahora debemos crear un adapter personalizado
	- Creamos una clase llamada ClienteAdapter
		- Declaramos dos Atributos
		```
		private Context context;
		private List<Cliente> clientes;
		```
		- Generamos un constructor con esos dos parámetros.
		- Colocamos extends BaseAdapter
		- Implementamos todos los métodos con la ayuda del IDE.
		- Método getView():
		```
		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			/*Acá cargo el layout del item cliente*/
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

			return view;
		}
		```
## XML res/layout/fragment_listar.xml
- Método onCreateView()
```
View v = inflater.inflate(R.layout.fragment_listar, container, false);
ListView lvClientes = (ListView)v.findViewById(R.id.listaNombres);

Cliente c1 = new Cliente("Nombre 1",10,"Ciudad 1");
Cliente c2 = new Cliente("Nombre 2",20,"Ciudad 2");
Cliente c3 = new Cliente("Nombre 3",30,"Ciudad 3");

List<Cliente> clientes = new ArrayList<>();
clientes.add(c1);
clientes.add(c2);
clientes.add(c3);

lvClientes.setAdapter(new ClienteAdapter(v.getContext(), clientes));

return v;
```

