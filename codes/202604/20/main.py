# main.py
# 이 파일은 텍스트 요약 프로그램의 메인 진입점입니다.

from summarizer import TextSummarizer
import sys

def main():
    # TextSummarizer 클래스를 초기화합니다.
    # 모델 로딩은 시간이 걸릴 수 있으므로, 한 번만 수행합니다.
    summarizer = TextSummarizer()

    print("--- AI 기반 텍스트 요약 프로그램 ---")
    print("요약할 텍스트를 입력해주세요. 입력을 마치려면 새로운 줄에 'EOF'를 입력하세요.")
    print("-" * 40)

    # 사용자로부터 여러 줄의 텍스트를 입력받습니다.
    input_lines = []
    while True:
        try:
            line = input()
            if line.strip().upper() == 'EOF':
                break
            input_lines.append(line)
        except EOFError:
            break
        except KeyboardInterrupt:
            print("
프로그램을 종료합니다.")
            sys.exit(0)

    if not input_lines:
        print("입력된 텍스트가 없습니다. 프로그램을 종료합니다.")
        return

    text_to_summarize = "
".join(input_lines)

    print("
[원문 텍스트]")
    print(text_to_summarize)
    print("-" * 40)

    print("[요약 중...]")
    # 텍스트 요약 함수를 호출합니다.
    summary = summarizer.summarize_text(text_to_summarize)

    print("
[요약 결과]")
    print(summary)
    print("-" * 40)

if __name__ == "__main__":
    main()
