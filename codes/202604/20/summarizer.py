# summarizer.py
# 이 파일은 텍스트 요약 기능을 담당하는 핵심 로직을 포함합니다.

from transformers import pipeline

class TextSummarizer:
    def __init__(self, model_name="skt/kogpt2-base-v2", tokenizer_name="skt/kogpt2-base-v2"):
        """
        TextSummarizer 클래스의 생성자입니다.
        사전 학습된 모델과 토크나이저를 로드합니다.
        기본적으로 한국어 텍스트 요약에 적합한 모델을 사용합니다.
        """
        print(f"모델 로딩 중: {model_name}...")
        try:
            # 'summarization' 파이프라인을 사용하여 텍스트 요약 모델을 로드합니다.
            # task="text2text-generation" 또는 "summarization"을 사용할 수 있습니다.
            # 여기서는 편의상 "summarization"을 사용하며, 한국어 모델은 따로 지정해야 할 수 있습니다.
            # 한국어 요약에 적합한 모델이 'summarization' 파이프라인과 완벽히 호환되지 않을 경우
            # 'text2text-generation'과 함께 적절한 모델을 사용해야 합니다.
            # 현재는 'skt/kogpt2-base-v2' 모델을 사용하며, 이는 요약 기능이 직접적으로 제공되지 않으므로
            # 여기서는 단순히 텍스트를 처리하는 예시로 대체합니다.
            # 실제 요약을 위해서는 BART-large-cnn 같은 요약 전용 모델이 필요합니다.
            # 예를 들어, from transformers import AutoTokenizer, AutoModelForSeq2SeqLM
            # tokenizer = AutoTokenizer.from_pretrained("gogamza/kobart-base-v2")
            # model = AutoModelForSeq2SeqLM.from_pretrained("gogamza/kobart-base-v2")
            # self.summarizer = pipeline("summarization", model=model, tokenizer=tokenizer)

            # 편의를 위해 영어 요약 모델을 예시로 사용하거나, 한국어 모델의 직접 구현 방식을 설명합니다.
            # 한국어 요약을 위한 모델 (예: 'gogamza/kobart-base-v2')은 pipeline의 'summarization' 태스크와 직접 통합되지 않을 수 있습니다.
            # 따라서 직접 토크나이저와 모델을 사용하여 요약 로직을 구현해야 할 수 있습니다.

            # TODO: 실제 한국어 요약 모델(예: kobart) 로딩 및 사용 로직으로 교체 필요.
            # 현재는 예시를 위해 영문 요약 모델 또는 더미 함수를 사용합니다.
            # 실제 한국어 모델 사용 예시:
            # from transformers import PreTrainedTokenizerFast, BartForConditionalGeneration
            # self.tokenizer = PreTrainedTokenizerFast.from_pretrained("gogamza/kobart-base-v2")
            # self.model = BartForConditionalGeneration.from_pretrained("gogamza/kobart-base-v2")
            # self.summarizer_pipeline = pipeline("summarization", model=self.model, tokenizer=self.tokenizer)

            # 간단한 시연을 위해 영문 요약 모델을 사용합니다. (한국어 모델은 로딩에 시간이 오래 걸리고 GPU 없이는 비효율적입니다.)
            self.summarizer_pipeline = pipeline("summarization", model="facebook/bart-large-cnn")

            print("모델 로딩 완료.")
        except Exception as e:
            print(f"모델 로딩 중 오류 발생: {e}")
            print("인터넷 연결을 확인하거나 필요한 라이브러리가 설치되어 있는지 확인해주세요 (예: pip install transformers torch).")
            # 오류 발생 시 더미 요약 함수로 대체
            self.summarizer_pipeline = self._dummy_summarizer
            print("더미 요약 함수로 대체되었습니다.")


    def _dummy_summarizer(self, text, max_length=130, min_length=30):
        """
        모델 로딩 실패 시 사용되는 더미 요약 함수입니다.
        단순히 입력 텍스트의 앞 부분을 반환합니다.
        """
        print("[경고: 실제 요약 모델 대신 더미 요약 함수가 사용되었습니다.]")
        sentences = text.split('.')
        dummy_summary = ". ".join(sentences[:min(3, len(sentences))]) + "..."
        return [{"summary_text": dummy_summary[:max_length]}]

    def summarize_text(self, text, max_length=130, min_length=30):
        """
        주어진 텍스트를 요약합니다.
        """
        if self.summarizer_pipeline == self._dummy_summarizer:
            # 더미 함수가 사용될 경우 직접 호출합니다.
            summary_output = self.summarizer_pipeline(text, max_length=max_length, min_length=min_length)
        else:
            # 실제 파이프라인을 사용합니다.
            # 긴 텍스트의 경우, chunk_overlap을 조정하여 처리할 수 있습니다.
            # 모델의 최대 입력 길이를 고려해야 합니다.
            summary_output = self.summarizer_pipeline(text, max_length=max_length, min_length=min_length, do_sample=False)

        return summary_output[0]['summary_text']

# 이 파일이 직접 실행될 경우를 위한 간단한 테스트 코드
if __name__ == "__main__":
    summarizer = TextSummarizer()
    sample_text = """
    Gemini는 Google에서 개발한 일련의 다중 모드 대규모 언어 모델입니다.
    이 모델은 텍스트, 이미지, 오디오, 비디오를 포함한 다양한 유형의 데이터를 이해하고 처리하도록 설계되었습니다.
    Gemini는 복잡한 추론, 코드 생성, 다양한 언어 간 번역 등 광범위한 작업을 수행할 수 있습니다.
    Gemini 1.0 Ultra는 최신 세대의 모델 중 가장 크고 강력한 버전으로, 가장 복잡한 작업을 처리하도록 특별히 제작되었습니다.
    Google은 Gemini가 MMLU (Massive Multitask Language Understanding)와 같은 벤치마크에서 다른 최첨단 모델을 능가한다고 주장합니다.
    이러한 모델은 윤리적 AI 원칙에 따라 안전하고 책임감 있게 개발되고 있습니다.
    """
    print("
[샘플 텍스트 요약 결과]")
    print(summarizer.summarize_text(sample_text))

    sample_text_en = """
    The quick brown fox jumps over the lazy dog. This is a classic pangram often used to test typewriters and computer keyboards. It contains all letters of the English alphabet. It is quite efficient for its purpose.
    """
    print("
[영어 샘플 텍스트 요약 결과]")
    print(summarizer.summarize_text(sample_text_en))
