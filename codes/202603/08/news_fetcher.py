# -*- coding: utf-8 -*-

import requests
from bs4 import BeautifulSoup
from typing import Optional, Dict

def fetch_news_article(url: str) -> Optional[Dict[str, str]]:
    """
    주어진 URL에서 뉴스 기사의 제목과 본문을 추출합니다.
    Args:
        url (str): 뉴스 기사의 URL.
    Returns:
        Optional[Dict[str, str]]: 기사 제목과 본문을 담은 딕셔너리.
                                 정보를 추출하지 못하면 None을 반환합니다.
    """
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
    }
    try:
        response = requests.get(url, headers=headers, timeout=10)
        response.raise_for_status() # HTTP 오류 발생 시 예외 발생
        soup = BeautifulSoup(response.text, 'html.parser')

        # 제목 추출 (다양한 언론사 웹사이트 구조를 고려하여 일반적인 선택자 사용)
        title = soup.find('h1')
        if title:
            title = title.get_text().strip()
        else:
            # meta og:title 태그 확인
            og_title = soup.find('meta', property='og:title')
            if og_title and og_title.get('content'):
                title = og_title['content'].strip()
            else:
                title = "제목 없음"

        # 본문 추출 (다양한 언론사 웹사이트 구조를 고려하여 일반적인 선택자 사용)
        # p 태그들을 찾아 결합하는 방식이 가장 일반적입니다.
        body_tags = soup.find_all('p')
        article_body = ' '.join([p.get_text().strip() for p in body_tags if p.get_text().strip()])

        if not article_body:
            # div 태그 중 기사 본문으로 추정되는 클래스나 ID를 가진 것을 찾아볼 수 있습니다.
            # 이 부분은 특정 사이트에 따라 유동적으로 변경되어야 합니다.
            # 예시: article-body, article-content, news_content 등
            content_div = soup.find('div', class_='article_content') or 
                          soup.find('div', id='articleBodyContents') or 
                          soup.find('div', class_=re.compile(r'content|body|article', re.I))
            if content_div:
                article_body = content_div.get_text().strip()


        if not title and not article_body:
            print(f"경고: {url}에서 기사 정보를 추출하지 못했습니다.")
            return None

        return {"title": title, "body": article_body}

    except requests.exceptions.RequestException as e:
        print(f"뉴스 기사를 가져오는 중 오류 발생: {e}")
        return None
    except Exception as e:
        print(f"뉴스 기사 파싱 중 예기치 않은 오류 발생: {e}")
        return None

if __name__ == '__main__':
    # 테스트 코드
    # 실제 뉴스 기사 URL로 테스트해야 합니다.
    # 예시 URL (실제 동작 여부는 시간에 따라 달라질 수 있습니다.)
    sample_news_url = "https://n.news.naver.com/mnews/article/001/0013770451" # 연합뉴스
    # sample_news_url = "https://www.chosun.com/politics/president/2023/03/08/XYZABCD.html" # 조선일보 (예시)

    print(f"'{sample_news_url}' 에서 뉴스 기사 추출 시도...")
    article = fetch_news_article(sample_news_url)

    if article:
        print("
--- 추출된 기사 ---")
        print(f"제목: {article['title']}")
        print(f"본문 요약: {article['body'][:200]}...") # 본문이 길 수 있으므로 일부만 출력
    else:
        print("기사 추출 실패.")
