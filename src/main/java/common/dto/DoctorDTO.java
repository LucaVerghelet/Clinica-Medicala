package common.dto;

public class DoctorDTO {
    private Integer id;
    private String nume;
    private String email;
    private String specializare;
    private boolean necesitaRecomandare;

    public DoctorDTO(Integer id, String nume, String email, String specializare, boolean necesitaRecomandare) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.specializare = specializare;
        this.necesitaRecomandare = necesitaRecomandare;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Dr. " + nume + " (" + email + ") " +
                "\n\tSpecializare: " + specializare +
                "\n\tNecesita recomandare: " + (necesitaRecomandare ? "Da" : "Nu");
    }


}
