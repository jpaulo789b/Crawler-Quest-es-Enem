# Crawler para questões do enem


O crawler foi construido usando como base o framework pyhton scrapy .Nessesário ter em seu computador o python instalado juntamente com o scrapy

  # - Para Rodar o crawler
  - Unix/Linux
```sh
    git clone https://github.com/jpaulo789b/Crawler-Quest-es-Enem.git crawler
    cd crawler/indo/spiders
    scrapy runspider spiderquestoes.py
```
  - Para salvar os dados obtidos do crawler em um arquivo utilize
 ```sh
    scrapy runspider spiderquestoes.py -o Questoes.csv -t csv
    scrapy runspider spiderquestoes.py -o Questoes.xml -t xml
 ```

# No projeto está contido um projeto java simples que insere as questões no banco de dados do SGEP.
-Backup do banco de dados do SGEP em DadosObtidos arquivo ```BancoSGEP.sql``` **para banco POSTGRES**
 -projeto java na pasta ```InsertDB```
 -Na classe ReadCSV.java edite o valor da variavel ```fXmlFile``` colocando o caminho dos dados obtidos pelo crawler
 ```
 #Mude o caminho do arquivo para o caminho do arquivo em seu sistema
 File fXmlFile = new File("/home/harlock/crawler/indo/indo/spiders/todasquestos.xml");
 ```
   

