# Navigation Drawer

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
	```java
	Fragmento f = new Fragmento();
	fm.beginTransaction().replace(R.id.contenido, cf).commit();
	```
## Procesar un componente (botón) en un fragment
- Supongamos que el botón se llame btnGuardar
- En el método de onCreateView() del fragment:
```java
View v = inflater.inflate(R.layout.fragment_crear, container, false);
final Context c = v.getContext();
Button btnGuardar = (Button) v.findViewById(R.id.btnGuardar);
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
	
# Layout personalizado para un item de un listView
- Vamos a crear una clase Cliente con nombre, edad y ciudad (Generar constructor, getter y setter)
- En res/layout crear un nuevo EmptyActivity. Le llamamos ClienteItem
	- En él, diseñamos un item de un cliente (Por ejemplo 3 TextView hacia abajo)
	- Click secundario en los TextView --> Organize --> Expand Horizontally
	- En mi caso, coloque 3 (txtNombre, txtEdad, txtCiudad)
- Ahora debemos crear un adapter personalizado
	- Creamos una clase llamada ClienteAdapter
		- Declaramos dos Atributos
		```java
		private Context context;
		private List<Cliente> clientes;
		```
		- Generamos un constructor con esos dos parámetros.
		- Colocamos extends BaseAdapter
		- Implementamos todos los métodos con la ayuda del IDE.
		- Método getView():
		```java
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
## Clase ListarFragment.java
- Método onCreateView()
```java
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
# Conexión a SQLite
## Página Oficial
https://www.sqlite.org/index.html
## Documentación oficial
https://www.sqlite.org/docs.html
## Sintaxis oficial
https://www.sqlite.org/lang.html
## Tipos de datos oficial
https://www.sqlite.org/datatype3.html

## Dar permisos en AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

## Modificar build.gradle (Module:app) en Gradle Scripts
Acá debes cambiar el targetSdkVersion a tu versión. En mi caso es 5.0 (api 21)
```json	
targetSdkVersion 21
```

## Crear clase BD.java
Esta clase se encargará de crear la base de datos si es que esta no existe. En el caso siguiente, se crearán 3 tablas en base de datos. Es necesario que esta clase herede de la clase SQLiteOpenHelper.

```java
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper{

    private String tCliente =
		"CREATE TABLE cliente("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"nombre TEXT," +
			"sector TEXT," +
			"direccion TEXT," +
			"deuda INTEGER"+
		")";

    private String tMovimiento =
		"CREATE TABLE movimiento("+
			"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"fecha TEXT,"+ // TEXT as ISO8601 strings ("YYYY-MM-DD HH:MM:SS.SSS").
			"detalle TEXT,"+
			"saldo INTEGER,"+
			"cliente INTEGER," +
			"FOREIGN KEY(cliente) REFERENCES cliente(id)"+
		")";

	/*
	https://stackoverflow.com/questions/11643294/what-is-the-use-of-sqlitedatabase-cursorfactory-in-android?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
	*/
	
    public BD(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tCliente);
        db.execSQL(tMovimiento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*No implementado*/
    }

}
```

## Clase Cliente.java
Crear una clase Cliente que tenga los atributos requeridos.

## Clase Data.java
Esta clase es la intermediaria entre el lenguaje de programación y el motor de datos SQLite. 

### Imports necesarios
```java
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
```

### Atributos
```java
private Context contexto;
private BD conexion;
private SQLiteDatabase db;
private Cursor cursor;
private final String RUTA_BD =
            Environment.getExternalStorageDirectory().
                    getPath()+"/proyecto/nombre_bd.sqlite";
```

### Constructor
```java
public Data(Context contexto) {
	this.contexto = contexto;
}
```
### Método para inserción de datos en la base de datos
```java
public void crearCliente(Cliente c){
	conexion = new BD(contexto, RUTA_BD, null, 1);
    db = conexion.getWritableDatabase();

    String insert = "INSERT INTO cliente VALUES(null, '"+c.getNombre()+"', '"+c.getSector()+"', '"+c.getDireccion()+"', '"+c.getDeuda()+"')";
    Log.v("INSERT CLIENTE", insert);
    db.execSQL(insert);

    db.close();
}
```

### Método para obtener una lista de objetos
```java
public List<Cliente> getClientes(){
	List<Cliente> lista = new ArrayList<>();
	Cliente c = null;

	conexion = new BD(contexto, RUTA_BD, null, 1);
	db = conexion.getWritableDatabase();

	String select = "SELECT * FROM cliente";

	cursor = db.rawQuery(select, null);

	if(cursor.moveToFirst()){
		do{
			c = new Cliente();

			c.setId(cursor.getInt(0));
			c.setNombre(cursor.getString(1));
			c.setSector(cursor.getString(2));
			c.setDireccion(cursor.getString(3));
			c.setDeuda(cursor.getInt(4));

			lista.add(c);
		}while(cursor.moveToNext());
	}

	db.close();

	return lista;

}
```

## Solicitud de permisos en tiempo de ejecución
### Añadir en AndroidManifest
```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
<uses-permission android:name="android.permission.WRITE_CONTACTS"></uses-permission>
<uses-permission android:name="android.permission.CAMERA"></uses-permission>
<uses-permission android:name="android.permission.CALL_PHONE"></uses-permission>
```

### Programación de un botón
```java
public void btnProcesar_onClick(View v){
	int PER_GRAN = PackageManager.PERMISSION_GRANTED;

	int WC_PER = ActivityCompat.checkSelfPermission(
		this,
		Manifest.permission.WRITE_CONTACTS
	);

	int GPS_PER = ActivityCompat.checkSelfPermission(
		this,
		Manifest.permission.ACCESS_FINE_LOCATION
	);

	int CAM_PER = ActivityCompat.checkSelfPermission(
		this,
		Manifest.permission.CAMERA
	);

	int NS_PER = ActivityCompat.checkSelfPermission(
		this,
		Manifest.permission.CALL_PHONE
	);

	if(
		WC_PER != PER_GRAN ||
		GPS_PER != PER_GRAN ||
		CAM_PER != PER_GRAN ||
		NS_PER != PER_GRAN
	){
		ActivityCompat.requestPermissions(
			this,
			new String[]{
				Manifest.permission.WRITE_CONTACTS,
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.CAMERA,
				Manifest.permission.CALL_PHONE
			},
			1
		);
	}
}

@Override
public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
	if(requestCode == 1){
		int i = 0;
		for (int gr : grantResults) {
			if(gr == PackageManager.PERMISSION_GRANTED){
				Log.v("PERMISOS("+permissions[i]+")","SI");
			}else{
				Log.v("PERMISOS("+permissions[i]+")","NO");
			}
			i++;
		}
	}
}
```

## Listado de todos los permisos (desde java)
```java
StringBuilder sb = new StringBuilder(" \n");
final Field[] manifestFields = Manifest.permission.class.getDeclaredFields();
for (final Field field : manifestFields) {
    System.out.println(field.getName());
}
```
