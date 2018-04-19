# apuntes_android

Pasos Navigation Drawer

- Ir a res/layout/content_main.xml
	- android:id="@+id/contenido"
- Ir a res/menu/activity_main_drawer.xml
	- Acá creamos algunas opciones, como por ej: nav_crear
- Ahora creamos los fragments necesarios y modificamos los fragments xml
	- Además en las clases fragment, solo debe dejar el método onCreateView()
- Abrir MainActivity
- onNavigationItemSelected(MenuItem item)
	- Añadir FragmentManager fm = getSupportFragmentManager();
	- Generar un switch case según corresponda (Si se crearón 2 navs, se deben 		generar dos cases.)
	- En cada case:
		- Fragmento f = new Fragmento();
		- fm.beginTransaction().replace(R.id.contenido, cf).commit();


- Procesar un componente (botón) en un fragment
	- Supongamos que el botón se llame btnGuardar
	- En el método de onCreateView() del fragment:
		- View v = inflater.inflate(R.layout.fragment_crear, container, false);
		- final Context c = v.getContext();
		- btnGuardar.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            Toast.makeText(c, "Botón click!", Toast.LENGTH_SHORT).show();
		        }
		    });
		- return v;


- res/layout/nav_header_main.xml
	- Es aquí donde se cambia el icono de la barra lateral y los datos
	- Además es acá en donde se puede cambiar el fondo, por defecto esta android:background="@drawable/side_nav_bar"
	- Si quieres cambiar el fondo por una foto cualquiera, la copias desde el navegador de archivos del sistema operativo, y le pegas en res/drawable. Renombras la foto (Por ejemplo fondo) y cambias la propiedad android:background="@drawable/fondo" en res/layout/nav_header_main.xml

- res/drawable/side_nav_bar.xml
	- Es aquí donde se cambia el color de fondo de la barra lateral
	
