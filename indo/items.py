# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy import Item, Field

class IndoItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    alternativa_A = scrapy.Field()
    alternativa_B = scrapy.Field()
    alternativa_C = scrapy.Field()
    alternativa_D = scrapy.Field()
    alternativa_E = scrapy.Field()
    alternativaCorreta = scrapy.Field()
    pergunta = scrapy.Field()
    texto = scrapy.Field()
    questao = scrapy.Field()
