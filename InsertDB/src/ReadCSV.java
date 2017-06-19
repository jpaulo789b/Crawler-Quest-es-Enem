/**
 * Created by harlock on 18/06/17.
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV{

    public static List<Questao> linhas() {
        List<Questao> lista;
        lista = new ArrayList<>();
        try {

            File fXmlFile = new File("/home/harlock/crawler/indo/indo/spiders/todasquestos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("item");

            System.out.println("----------------------------");

            int total = nList.getLength();
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Questao questao = new Questao();
                    Element eElement = (Element) nNode;

                    questao.setAssunto(eElement.getAttribute("questao"));
                    questao.setEnunciado(ValorNode(eElement.getElementsByTagName("texto")));
                    questao.setEnunciado(questao.getEnunciado()+" " +ValorNode(eElement.getElementsByTagName("pergunta")));
                    questao.setDificuldade("Medio");
                    questao.setEstado("ABERTO");
                    questao.setObjetiva(true);
                    questao.setResposta(ValorNode(eElement.getElementsByTagName("alternativaCorreta")).split("'")[1]);
                    ///
                    if(!ValorNode(eElement.getElementsByTagName("alternativa_A")).equals("")){
                        Alternativa A = new Alternativa(); A.setIndex_alternativa(0);
                        A.setOrdem("A");
                        A.setDescricao(ValorNode(eElement.getElementsByTagName("alternativa_A")).substring(3));
                        questao.getAlternativas().add(A);
                    }


                    if(!ValorNode(eElement.getElementsByTagName("alternativa_B")).equals("")){
                        Alternativa B = new Alternativa(); B.setIndex_alternativa(1);
                        B.setOrdem("B");
                        B.setDescricao(ValorNode(eElement.getElementsByTagName("alternativa_B")).substring(3));
                        questao.getAlternativas().add(B);
                    }


                    if(!ValorNode(eElement.getElementsByTagName("alternativa_C")).equals("")){
                        Alternativa C = new Alternativa(); C.setIndex_alternativa(2);
                        C.setOrdem("C");
                        C.setDescricao(ValorNode(eElement.getElementsByTagName("alternativa_C")).substring(3));
                        questao.getAlternativas().add(C);

                    }


                    if(!ValorNode(eElement.getElementsByTagName("alternativa_D")).equals("")){
                        Alternativa D = new Alternativa(); D.setIndex_alternativa(3);
                        D.setOrdem("D");
                        D.setDescricao(ValorNode(eElement.getElementsByTagName("alternativa_D")).substring(3));
                        questao.getAlternativas().add(D);
                    }

                    if(!ValorNode(eElement.getElementsByTagName("alternativa_E")).equals("")){
                        Alternativa E = new Alternativa(); E.setIndex_alternativa(4);
                        E.setOrdem("E");
                        E.setDescricao(ValorNode(eElement.getElementsByTagName("alternativa_E")).substring(3));
                        questao.getAlternativas().add(E);
                    }


                    lista.add(questao);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    private static String ValorNode(NodeList node){
        if (node.getLength() > 0) {
            String temp = node.item(0).getTextContent();
            return temp;
        }else{
            return "";
        }
    }
    private static boolean itIsNotNull(NodeList node){
        if (node.getLength() > 0) {
            return true;
        }else{
            return false;
        }
    }

}
