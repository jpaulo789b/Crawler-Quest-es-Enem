# -*- coding: utf-8 -*-
import scrapy
from scrapy.spiders import Rule
from scrapy.linkextractors import LinkExtractor
from lxml import html
from indo.items import IndoItem


class SpiderquestoesSpider(scrapy.Spider):
    name = "spiderquestoes"
    allowed_domains = ["www.gabarite.com.br"]
    start_urls = [
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/13-administracao',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/85-administracao-financeira-e-orcamentaria',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/68-agronomia',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/12-arquivologia',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/75-atualidades',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/69-biblioteconomia',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/20-direito-do-trabalho',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/39-geografia',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/38-historia',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/37-ingles',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/1-portugues',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/40-quimica',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/78-telecomunicacoes',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/4-informatica',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/89-governanca-de-ti',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/61-direitos-humanos',
            'http://www.gabarite.com.br/questoes-de-concursos/disciplina/51-biologia'
    ]

    def parse(self, response):

        tree = html.parse(response.url)
        conteudo = tree.xpath('//*[@id="content_questoes_conteudo"]')[0]
        forms = conteudo.xpath('form')
        # Inicio do crawler
        for idx, questao in enumerate(conteudo.xpath('ul')):
            item = IndoItem();
            if len(questao.xpath('li[@class="numero"]')):
                item['questao'] = questao.xpath('li[@class="numero"]')[0].text_content()

            if len(questao.xpath('li[@class="texto"]')):
                item['texto'] = questao.xpath('li[@class="texto"]')[0].text_content()

            if len(questao.xpath('li[@class="pergunta"]')):
                item['pergunta'] = questao.xpath('li[@class="pergunta"]')[0].text_content()
            alternativas = forms[idx].xpath('li/fieldset/label')
            item['alternativa_A'] = alternativas[0].text if len(alternativas) > 0 else ''
            item['alternativa_B'] = alternativas[1].text if len(alternativas) > 1 else ''
            item['alternativa_C'] = alternativas[2].text if len(alternativas) > 2 else ''
            item['alternativa_D'] = alternativas[3].text if len(alternativas) > 3 else ''
            item['alternativa_E'] = alternativas[4].text if len(alternativas) > 4 else ''
            item['alternativaCorreta'] = forms[idx].xpath('button/@onclick')[0]
            inputString = str(item['alternativaCorreta']).split("checaquestao", 1)[1]
            item['alternativaCorreta'] = str(inputString).split(',')[1]
            yield item
            print item
        for link in response.css('#content_paginacao > a::attr(href)').extract():
            yield scrapy.Request(response.urljoin(link), callback=self.parse)

#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/13-administracao',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/85-administracao-financeira-e-orcamentaria',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/68-agronomia',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/12-arquivologia',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/75-atualidades',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/69-biblioteconomia',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/20-direito-do-trabalho',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/39-geografia',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/38-historia',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/37-ingles',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/1-portugues',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/40-quimica',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/78-telecomunicacoes',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/4-informatica',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/89-governanca-de-ti',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/61-direitos-humanos',
#     'http://www.gabarite.com.br/questoes-de-concursos/disciplina/51-biologia'