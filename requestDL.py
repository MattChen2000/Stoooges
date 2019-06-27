import requests
count = 52

urlList = []
urlList.append('http://top.zhan.com/toefl/read/alltpo.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo48.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo44.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo40.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo36.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo32.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo28.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo24.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo20.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo16.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo12.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo8.html')
urlList.append('http://top.zhan.com/toefl/read/alltpo4.html')

for url in urlList:
    r = requests.get(url)
    open('TPO{}.html'.format(count), 'w', encoding='utf8').write(r.text)
    count -= 4
 
