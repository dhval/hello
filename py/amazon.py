import re
import requests

nreviews_re = {'com': re.compile('\d[\d,]+(?= customer review)'), 
               'co.uk':re.compile('\d[\d,]+(?= customer review)'),
               'de': re.compile('\d[\d\.]+(?= Kundenrezens\w\w)')}
no_reviews_re = {'com': re.compile('no customer reviews'), 
                 'co.uk':re.compile('no customer reviews'),
                 'de': re.compile('Noch keine Kundenrezensionen')}

def get_number_of_reviews(asin, country='com'):                                 
    url = 'http://www.amazon.{country}/product-reviews/{asin}'.format(country=country, asin=asin)
    html = requests.get(url).text
    try:
        return int(re.compile('\D').sub('',nreviews_re[country].search(html).group(0)))
    except:
        if no_reviews_re[country].search(html):
            return 0
        else:
            return None  # to distinguish from 0, and handle more cases if necessary

if __name__ == "__main__":
#   '1433524767', 'co.uk'    
    print get_number_of_reviews('B00OC0USA6', 'com')