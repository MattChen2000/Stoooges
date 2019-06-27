import requests
from bs4 import BeautifulSoup
import re
import csv
import os

# This function retrieves all urls containing test questsions and returns them in a list
def get_url_list():
      # Since every url includes four TPO practice tests, we inititate a countdown from 52,
      # which decreases by four every time the loop runs
      count = 52
      urls = []

      while count > 0:
          fhand = open('TPO{}.html'.format(count), encoding='utf8')
          src = fhand.read()
          soup = BeautifulSoup(src, 'lxml')
          websites = soup.find_all('a', href=re.compile('http://top.zhan.com/toefl/read/practicereview-[\d]{1,4}-13.html'))
          # print(len(websites))
          # print(websites[0])
          for website in websites:
              link = website['href']
              pos = link.find('.html')
              for i in range(15):
                  urls.append(link[:pos] + '-0-' + str(i) + '.html')
          count -= 4
      # print(urls)
      return urls

# This function retrieves the html files based on the urls returned by get_url_list()
def get_html_from_url(url_list, html_dir):
      leng = len(url_list)
      if not os.path.exists(html_dir):
            os.makedirs(html_dir) 
      for i in range(leng):
            print('{} // {}:\nSpidering {}'.format(str(i+1), str(leng), url_list[i]))
            filename = '{}.html'.format(i+1)

            if os.path.exists(html_dir + filename):
                  print(filename, 'exists, skipped')
                  continue

            try:
                  my_header = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'}
                  res = requests.get(url_list[i], headers=my_header)
                  with open(html_dir + filename,'w',encoding='utf8') as fw:
                        fw.write(res.text)
            except:
                  print('fail to connect {}, try again later'.format(url_list[i]))
                  continue

# This function finds the vocabulary tags and returns them in a list
def parse_htmls():
      # 用BeautifulSoup洗网页
      vocabs = []                           # vocabs is the final yield list

      for i in range(1, 2340):              # It is known that there are 2340 html files
            vocab = []                      # vocab is a temporary list that stores the vocab of each html file
            # result = requests.get(url)
            # src = result.content
            fhand = open('HTMLS{}.html'.format(i), encoding='utf8')
            source = fhand.read()
            # print(len(src))
            # print(src[:500])
            soup = BeautifulSoup(source, 'lxml')

            question = soup.find_all('div', class_="left text")
            # print(question)
            try:
                question_str = str(question[0])
            except:
                continue

            if not 'is closest in meaning to' in question_str: continue

            x = question[0].span.string
            vocab.append(x)

            answers = soup.find_all('p', class_="ops sec")
            for answer in answers:
                word = answer.label
                vocab.append(word.string)
            vocabs.append(vocab)
            fhand.close()

      return vocabs

# This function writes the vocabulary list to a csv file
def write_file(data, path):
    if not os.path.exists(path):
          os.makedirs(path)
    with open(path + 'TPOVocab.csv', 'w', encoding='utf8') as csvfile:
        vocabwriter = csv.writer(csvfile)
        for item in data:
            vocabwriter.writerow([item[0], item[1], item[2], item[3], item[4]])
    return


def main():

      url_list = get_url_list()
      get_html_from_url(url_list, html_dir)
      data = parse_htmls()
      print(len(data))
      path = "C:\\users\\mattchen2000\\documents\\stoooges intern\\6.25\\CSV"
      write_file(data, path)

if __name__ == '__main__':
      main()
