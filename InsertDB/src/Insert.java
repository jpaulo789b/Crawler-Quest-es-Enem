import sun.rmi.runtime.Log;

import java.sql.*;
import java.util.List;

/**
 * Created by harlock on 18/06/17.
 */
public class Insert {

    public static void main(String args[]) throws Exception {
        Connection stmt = null;
        DBConection dbConection = new DBConection();
        stmt = dbConection.db();

        String SQLQuestao = "INSERT INTO public.questao(" +
                " assunto, dificuldade, enunciado, estado, objetiva, resposta, " +
                " professor_id, id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String SQLAlternativa = "INSERT INTO public.alternativaquestao(" +
                " descricao, ordem, resposta, questao_id, index_alternativa, id)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        int i = 1;
        String maxQ = "select id from questao where id = (select max(id) from questao)";
        String maxA = "select id from alternativaquestao where id = (select max(id) from alternativaquestao) ";
        int contadorTOTAL = 0;
        for (Questao ques : ReadCSV.linhas()) {

            if(ques.getEnunciado().length() < 2000 && alternativasMuitoGrande(ques.getAlternativas())){
                PreparedStatement queryTotalQuestoes = stmt.prepareStatement(maxQ);
                ResultSet rsQ = queryTotalQuestoes.executeQuery();
                if(rsQ.next()){
                    ques.setID((rsQ.getInt("id")+1));
                }else {
                    ques.setID(1);
                }

                PreparedStatement inserirQuestao = stmt.prepareStatement(SQLQuestao);
                inserirQuestao.setString(1,ques.getAssunto());
                inserirQuestao.setString(2,ques.getDificuldade());
                inserirQuestao.setString(3,ques.getEnunciado());
                inserirQuestao.setString(4,ques.getEstado());
                inserirQuestao.setBoolean(5,ques.getObjetiva());
                inserirQuestao.setString(6,ques.getResposta());
                inserirQuestao.setInt(7,1);
                inserirQuestao.setInt(8,ques.getID());
                inserirQuestao.execute();
                PreparedStatement psAlternativa = stmt.prepareStatement(maxA);
                ResultSet rsA = psAlternativa.executeQuery();
                int contador = 0;
                if (rsA.next()){
                    contador = rsA.getInt("id");
                    contador++;
                }else {
                    contador = 1;
                }

                for (Alternativa alternativa: ques.getAlternativas()){
                    alternativa.setID(contador++);
                    alternativa.setQuestao_id(ques.getID());
                    PreparedStatement inserirAlternativa = stmt.prepareStatement(SQLAlternativa);
                    // descricao, ordem, resposta, questao_id, index_alternativa
                    inserirAlternativa.setString(1,alternativa.getDescricao());
                    inserirAlternativa.setString(2, String.valueOf(alternativa.getOrdem()));
                    inserirAlternativa.setBoolean(3,alternativa.isResposta());
                    inserirAlternativa.setInt(4,alternativa.getQuestao_id());
                    inserirAlternativa.setInt(5,alternativa.getIndex_alternativa());
                    inserirAlternativa.setInt(6,alternativa.getID());
                    inserirAlternativa.execute();

                }


                String questaotemplate = "INSERT INTO public.questaotemplate(id, questao_id)VALUES (?, ?)";
                PreparedStatement inserirQuestaotemplate = stmt.prepareStatement(questaotemplate);
                inserirQuestaotemplate.setInt(1,i++);
                inserirQuestaotemplate.setInt(2,ques.getID());
                inserirQuestaotemplate.execute();

                System.out.println("TOTAL DE QUESTÔES INSERIDAS:" + contadorTOTAL++);
            }

        }
    }

    public static boolean alternativasMuitoGrande(List<Alternativa> alternativas){
        boolean re = true;
        for (Alternativa alternativa: alternativas) {
            if(alternativa.getDescricao().length() > 255) re = false;
        }
        return  re;
    }
}
