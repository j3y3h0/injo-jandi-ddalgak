# -*- coding: utf-8 -*-

from news_fetcher import fetch_news_article
from nlp_processor import SentimentAnalyzer
from utils import clean_text, save_to_file
import os

def main():
    """
    뉴스 감성 분석 프로젝트의 메인 실행 함수입니다.
    뉴스 기사를 수집하고 감성 분석을 수행하여 결과를 출력합니다.
    """
    print("--- 한국어 뉴스 감성 분석 프로젝트 시작 ---")

    # 분석할 뉴스 기사 URL (예시)
    # 실제 기사는 URL이 유효하고 HTML 구조가 파싱 가능한 형태여야 합니다.
    # 네이버 뉴스 기사 예시 URL을 사용합니다.
    news_url = "https://n.news.naver.com/mnews/article/001/0013770451" # 연합뉴스 (예시)
    # 다른 뉴스로 변경하여 테스트해 볼 수 있습니다.

    print(f"
[1/3] 뉴스 기사 수집 중: {news_url}")
    article_data = fetch_news_article(news_url)

    if article_data:
        title = article_data.get("title", "제목 없음")
        body = article_data.get("body", "")

        print(f"   제목: {title}")
        # 본문이 길 경우 일부만 출력
        print(f"   본문 요약: {body[:150]}...
")

        print("[2/3] 뉴스 본문 텍스트 정제 중...")
        cleaned_body = clean_text(body)
        print(f"   정제된 본문 요약: {cleaned_body[:150]}...
")

        print("[3/3] 뉴스 본문 감성 분석 중...")
        analyzer = SentimentAnalyzer()
        sentiment_score, sentiment_label = analyzer.analyze_sentiment(cleaned_body)

        print("
--- 분석 결과 ---")
        print(f"분석 대상 뉴스: {title}")
        print(f"감성 점수: {sentiment_score:.2f}")
        print(f"감성 라벨: {sentiment_label}")

        # 결과를 파일로 저장
        output_filename = "sentiment_analysis_result.txt"
        result_content = f"--- 뉴스 감성 분석 결과 ---
" 
                         f"URL: {news_url}
" 
                         f"제목: {title}
" 
                         f"감성 점수: {sentiment_score:.2f}
" 
                         f"감성 라벨: {sentiment_label}

" 
                         f"--- 원문 본문 ---
{body}

" 
                         f"--- 정제된 본문 ---
{cleaned_body}"
        save_to_file(output_filename, result_content)
        print(f"
분석 결과가 '{output_filename}' 파일에 저장되었습니다.")

    else:
        print("뉴스 기사 수집에 실패하여 감성 분석을 진행할 수 없습니다.")

    print("
--- 프로젝트 종료 ---")

if __name__ == "__main__":
    main()
