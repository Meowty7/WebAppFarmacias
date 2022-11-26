package ds.proyecto.webappfarmacias.database;

public class Medicamentos {
    private String nombreGeneric, nombreComercial;
    private int idMedicamento;
    private float precioFabricante;

    public int getIdMedicamento() {
        return idMedicamento;
    }
    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
    public String getNombreGeneric() {
        return nombreGeneric;
    }
    public void setNombreGeneric(String nombreGeneric) {
        this.nombreGeneric = nombreGeneric;
    }
    public String getNombreComercial() {
        return nombreComercial;
    }
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    public float getPrecioFabricante() {
        return precioFabricante;
    }
    public void setPrecioFabricante(float precioFabricante) {
        this.precioFabricante = precioFabricante;
    }
}
