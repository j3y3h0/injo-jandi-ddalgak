# main.py
# 이 파일은 텍스트 감성 분석 프로젝트의 메인 실행 파일입니다.
# data_loader에서 데이터를 로드하고, sentiment_analyzer를 사용하여 감성을 분석한 후 결과를 출력합니다.

from data_loader import load_data
from sentiment_analyzer import SentimentAnalyzer

def main():
    """
    메인 함수: 데이터를 로드하고 감성 분석을 수행한 후 결과를 출력합니다.
    """
    print("--- 텍스트 감성 분석 프로젝트 시작 ---")

    # 1. 데이터 로드
    print("
[데이터 로드 중...]")
    texts_to_analyze = load_data()
    print(f"총 {len(texts_to_analyze)}개의 텍스트를 로드했습니다.")

    # 2. 감성 분석기 초기화
    print("
[감성 분석 수행 중...]")
    analyzer = SentimentAnalyzer()

    # 3. 각 텍스트에 대해 감성 분석 수행 및 결과 출력
    results = []
    for i, text in enumerate(texts_to_analyze):
        polarity, label = analyzer.analyze_sentiment(text)
        results.append({
            "text": text,
            "polarity": polarity,
            "sentiment": label
        })
        print(f"텍스트 {i+1}: '{text}'")
        print(f"  -> 극성: {polarity:.2f}, 감성: {label}")

    print("
--- 분석 완료 ---")
    print("
[요약]")
    positive_count = sum(1 for res in results if res["sentiment"] == "긍정")
    negative_count = sum(1 for res in results if res["sentiment"] == "부정")
    neutral_count = sum(1 for res in results if res["sentiment"] == "중립")

    print(f"긍정적인 문장: {positive_count}개")
    print(f"부정적인 문장: {negative_count}개")
    print(f"중립적인 문장: {neutral_count}개")

    print("
프로그램을 종료합니다.")

if __name__ == "__main__":
    main()
