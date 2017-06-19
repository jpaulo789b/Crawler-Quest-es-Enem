/**
 * Created by harlock on 18/06/17.
 */
public class Alternativa {
    private Integer ID;
    private String descricao;
    private String ordem;
    private boolean resposta;
    private Integer questao_id;
    private Integer index_alternativa;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public boolean isResposta() {
        return resposta;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }

    public Integer getQuestao_id() {
        return questao_id;
    }

    public void setQuestao_id(Integer questao_id) {
        this.questao_id = questao_id;
    }

    public Integer getIndex_alternativa() {
        return index_alternativa;
    }

    public void setIndex_alternativa(Integer index_alternativa) {
        this.index_alternativa = index_alternativa;
    }

}
