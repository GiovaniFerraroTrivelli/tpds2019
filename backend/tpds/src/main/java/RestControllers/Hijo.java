package RestControllers;

import java.util.ArrayList;

public class Hijo{
    private Integer edad;
    private String estadoCivil;
    private String sexo;

	private static ArrayList<Hijo> lista = new ArrayList<Hijo>();

	Hijo(Integer edad, String estadoCivil, String sexo) {
		this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
	}

	public static ArrayList<Hijo> getLista() {
		return lista;
	}

	public static void blankList() {
		lista.clear();
	}

	public static void addToList(Hijo hijo) {
		lista.add(hijo);
	}

    public void setEdad(Integer edad) {
		this.edad = edad;
    }
    
    public Integer getEdad() {
		return edad;
	}
    
    public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
    }
    
    public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
    }
    
    public String getSexo() {
		return sexo;
	}

}
