# 영화 추천 시스템 (Movie Recommendation System)

## 프로젝트 개요

이 프로젝트는 협업 필터링(Collaborative Filtering) 기법을 사용하여 사용자에게 맞춤형 영화를 추천하는 소규모 시스템입니다. 사용자 기반 협업 필터링을 통해 비슷한 취향을 가진 사용자들을 찾아내고, 이들이 선호하는 영화를 추천 목록으로 제공합니다. 본 프로젝트는 산업에서 널리 사용되는 추천 알고리즘의 기본 원리를 이해하고 실제 적용해보는 것을 목표로 합니다.

## 사용 기술

*   **언어**: Python
*   **라이브러리**:
    *   `pandas`: 데이터 처리 및 분석
    *   `scikit-learn`: 코사인 유사도(cosine similarity) 계산 등 머신러닝 유틸리티

## 파일 구조

*   `data_loader.py`:
    *   더미 사용자-영화 평점 데이터를 생성하고 로드하는 기능을 담당합니다.
    *   실제 서비스에서는 데이터베이스나 파일 저장소에서 데이터를 불러오는 부분에 해당합니다.
*   `recommender.py`:
    *   `MovieRecommender` 클래스를 정의합니다.
    *   사용자-영화 평점 매트릭스 생성, 사용자 간 유사도 계산 (코사인 유사도), 그리고 특정 사용자에게 영화를 추천하는 핵심 로직이 구현되어 있습니다.
*   `main.py`:
    *   프로젝트의 메인 실행 파일입니다.
    *   `data_loader.py`를 사용하여 데이터를 로드하고, `recommender.py`의 `MovieRecommender` 클래스를 초기화하여 영화 추천 과정을 시작합니다.
    *   특정 사용자에게 영화를 추천하고 그 결과를 출력합니다.
*   `README.md`:
    *   현재 문서로, 프로젝트에 대한 설명, 사용 방법 등을 담고 있습니다.

## 실행 방법

이 프로젝트는 Python으로 작성되었으며, `pandas`와 `scikit-learn` 라이브러리가 필요합니다.

1.  **필수 라이브러리 설치**:
    프로젝트를 실행하기 전에 필요한 라이브러리를 설치해야 합니다. 다음 명령어를 터미널에 입력하여 설치합니다.

    ```bash
    pip install pandas scikit-learn numpy
    ```

2.  **프로젝트 실행**:
    설치가 완료되면, `main.py` 파일을 실행하여 영화 추천 시스템을 가동할 수 있습니다.

    ```bash
    python main.py
    ```

    `main.py`를 실행하면 `data_loader.py`에서 더미 데이터가 생성되고, `recommender.py`에서 추천 로직이 수행된 후, 최종 추천 영화 목록이 콘솔에 출력됩니다.

## 코드 설명 (주요 함수 및 클래스)

### `data_loader.py`

*   `load_data()`: 임의의 사용자-영화 평점 데이터를 생성하여 `pandas` DataFrame 형태로 반환합니다. `user_id`, `movie_id`, `rating` 컬럼을 포함합니다.

### `recommender.py`

*   **클래스**: `MovieRecommender`
    *   **`__init__(self, ratings_df)`**: 추천 시스템을 초기화합니다. 평점 DataFrame을 받아 사용자-영화 매트릭스를 만들고 사용자 유사도를 미리 계산합니다.
    *   **`_preprocess_data()`**: `ratings_df`를 `pivot_table`을 이용하여 사용자-영화 매트릭스로 변환합니다. 평점이 없는 부분은 `0`으로 채워집니다.
    *   **`_calculate_user_similarity()`**: `scikit-learn`의 `cosine_similarity`를 사용하여 사용자-영화 매트릭스로부터 사용자 간의 코사인 유사도를 계산합니다.
    *   **`recommend_movies(self, user_id, num_recommendations=5)`**: 특정 `user_id`에게 `num_recommendations` 수만큼의 영화를 추천합니다. 유사도가 높은 사용자들의 평점을 기반으로 가중 평균을 사용하여 추천 점수를 계산하고, 아직 보지 않은 영화 중에서 점수가 높은 영화를 반환합니다.

### `main.py`

*   **`main()`**: 프로그램의 진입점입니다.
    1.  `load_data()`를 호출하여 평점 데이터를 가져옵니다.
    2.  `MovieRecommender` 객체를 생성하고 초기화합니다.
    3.  `recommender.recommend_movies()`를 호출하여 특정 사용자 ('user_1', 'user_5' 등)에 대한 추천을 수행하고 결과를 출력합니다.

---

이 프로젝트는 추천 시스템의 기본적인 개념을 파악하고 간단한 구현을 통해 동작 원리를 이해하는 데 도움이 될 것입니다.
