package clases;

import java.util.Scanner;

import android.os.Parcel;
import android.os.Parcelable;

public class Jugador implements Parcelable {

	String nombre;
	int avatar;
	String color;
	int id;

	public Jugador(int id, String color) {
		this.id = id;
		this.color = color;
	}

	public Jugador(int id, String color, String nombre) {
		this.id = id;
		this.color = color;
		this.nombre = nombre;
	}

	public Jugador(int id, String color, String nombre, int avatar) {
		this.id = id;
		this.color = color;
		this.nombre = nombre;
		this.avatar = avatar;
	}

	public Jugador(Parcel source) {
		id = source.readInt();
		color = source.readString();
		nombre = source.readString();
		avatar = source.readInt();
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Jugador createFromParcel(Parcel in) {
			return new Jugador(in);
		}

		public Jugador[] newArray(int size) {
			return new Jugador[size];
		}
	};

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(color);
		dest.writeString(nombre);
		dest.writeInt(avatar);
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public String getNombre() {
		return nombre;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public int[] ponerLinea(Tablero t) {
		Scanner sc = new Scanner(System.in);
		boolean movimientoRealizado = false;
		Linea l = null;
		int x = -1, y = -1;

		while (!movimientoRealizado) {
			System.out.println("Selecciona el número de línea.");
			x = sc.nextInt();
			System.out
					.println("La línea es vertical u horizontal; 0 = horizontal; 1 = vertical");
			y = sc.nextInt();

			l = t.getLinea(x, y);
			if (!l.estaCompleta()) {
				// Prefiero no completar la línea aquí, ya que un usuario
				// malintencionado
				// podría trucar la aplicación y poner donde no debe...

				movimientoRealizado = true;
			}
		}

		int[] a = { x, y };
		return a;
	}
}
