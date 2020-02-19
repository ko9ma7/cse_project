from urllib.parse import urlencode
import json_request as jr
import keys

BASE_URL_FACEBOOK_API = 'https://graph.facebook.com/v6.0'

 "https://graph.facebook.com/v6.0/me?fields=id%2Cname%2Cgender&access_token=

# 여러 파라미터에 대한 url 생성
def fb_generate_url(base=BASE_URL_FACEBOOK_API, node='', **param):
    return '%s/%s/?%s' % (base, node, urlencode(param))

# API 사용을 위한 페이지 id
def fb_name_to_id(pagename):
    url = fb_generate_url(node=pagename, access_token=keys.FACEBOOK_ACCESS_TOKEN)
    print(url)
    json_result = jr.json_request(url)
    print(json_result)
    return json_result.get('id')

# 게시글 가져오기(페이지 명과 게시글 일자 기간이 입력)
def fb_fetch_post(pagename, since, until):
    url = fb_generate_url(
        node=fb_name_to_id(pagename) + '/posts',
        fields='id, message, link, name, type, shares, created_time, \
                reactions.limit(0).summary(true), \
                comments.limit(0).summary(true)',
        since=since,
        until=until,
        limit=30,
        access_token=keys.FACEBOOK_ACCESS_TOKEN
    )
    json_result = jr.json_request(url)
    return json_result

posts = fb_fetch_post('jtbcnews', '2018-05-01', '2018-05-30')
print(posts)