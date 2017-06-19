import java.util.ArrayList;
import java.util.List;

/**
 * Created by harlock on 18/06/17.
 */
public class Questao {
    private Integer ID;
    private String assunto;
    private String dificuldade;
    private String enunciado;
    private String estado;
    private Boolean objetiva;
    private Integer disciplina_id;
    private Integer professor_id;
    private String resposta;
    private List<Alternativa> alternativas;

    public Questao() {
        alternativas = new ArrayList<>();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getObjetiva() {
        return objetiva;
    }

    public void setObjetiva(Boolean objetiva) {
        this.objetiva = objetiva;
    }

    public Integer getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(Integer disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public Integer getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(Integer professor_id) {
        this.professor_id = professor_id;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Questao{" +
                "ID=" + ID +
                ", assunto='" + assunto + '\'' +
                ", dificuldade='" + dificuldade + '\'' +
                ", enunciado='" + enunciado + '\'' +
                ", estado='" + estado + '\'' +
                ", objetiva=" + objetiva +
                ", disciplina_id=" + disciplina_id +
                ", professor_id=" + professor_id +
                ", alternativas=" + alternativas +
                '}';
    }
}
