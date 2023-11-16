public class Banno extends Filtro{
    private String tipo;

    public Banno(int hilos, int maxHilosConcurrentes, String tipo) {
        super( hilos,  maxHilosConcurrentes);
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
