# recommender.py

import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity
import numpy as np

class MovieRecommender:
    """
    협업 필터링(사용자 기반)을 이용한 영화 추천 시스템.
    """
    def __init__(self, ratings_df):
        """
        추천 시스템을 초기화합니다.
        :param ratings_df: 사용자-영화 평점 데이터프레임 (user_id, movie_id, rating 컬럼 포함)
        """
        self.ratings_df = ratings_df
        self.user_similarity_matrix = None
        self.user_movie_matrix = None
        self._preprocess_data()
        self._calculate_user_similarity()

    def _preprocess_data(self):
        """
        평점 데이터를 사용자-영화 매트릭스 형태로 변환합니다.
        """
        print("데이터 전처리: 사용자-영화 매트릭스를 생성합니다.")
        # 피벗 테이블을 사용하여 사용자-영화 매트릭스 생성
        # NaN 값은 해당 영화에 대한 평점이 없음을 의미
        self.user_movie_matrix = self.ratings_df.pivot_table(
            index='user_id', columns='movie_id', values='rating'
        ).fillna(0)
        print(f"사용자-영화 매트릭스 헤드:
{self.user_movie_matrix.head()}")

    def _calculate_user_similarity(self):
        """
        사용자 간의 코사인 유사도를 계산하여 유사도 매트릭스를 생성합니다.
        """
        print("사용자 유사도를 계산합니다.")
        # 사용자-영화 매트릭스를 기반으로 사용자 간의 코사인 유사도 계산
        self.user_similarity_matrix = cosine_similarity(self.user_movie_matrix)
        self.user_similarity_matrix = pd.DataFrame(
            self.user_similarity_matrix,
            index=self.user_movie_matrix.index,
            columns=self.user_movie_matrix.index
        )
        print(f"사용자 유사도 매트릭스 헤드:
{self.user_similarity_matrix.head()}")

    def recommend_movies(self, user_id, num_recommendations=5):
        """
        특정 사용자에게 영화를 추천합니다.
        :param user_id: 영화를 추천받을 사용자 ID
        :param num_recommendations: 추천할 영화의 개수
        :return: 추천 영화 목록
        """
        print(f"
사용자 '{user_id}'에게 영화를 추천합니다.")
        if user_id not in self.user_movie_matrix.index:
            print(f"오류: 사용자 '{user_id}'를 찾을 수 없습니다.")
            return []

        # 해당 사용자와 다른 사용자들의 유사도 가져오기
        user_similarities = self.user_similarity_matrix[user_id].sort_values(ascending=False)
        # 자기 자신은 제외
        user_similarities = user_similarities.drop(user_id)

        # 이미 본 영화 목록
        watched_movies = self.user_movie_matrix.loc[user_id]
        watched_movies = watched_movies[watched_movies > 0].index.tolist()

        # 추천 점수를 저장할 딕셔너리
        movie_scores = {}

        # 유사한 사용자들의 평점을 기반으로 영화 점수 계산
        for similar_user, similarity_score in user_similarities.items():
            if similarity_score <= 0: # 유사도가 0 이하면 건너뛰기
                continue

            # 유사한 사용자가 본 영화 가져오기
            similar_user_ratings = self.user_movie_matrix.loc[similar_user]
            # 아직 안 본 영화 중에서 평점을 매긴 영화만 고려
            unwatched_and_rated = similar_user_ratings[
                (similar_user_ratings > 0) & (~similar_user_ratings.index.isin(watched_movies))
            ]

            for movie, rating in unwatched_and_rated.items():
                if movie not in movie_scores:
                    movie_scores[movie] = 0
                # 유사도와 평점을 곱하여 점수에 추가 (가중 평균)
                movie_scores[movie] += similarity_score * rating

        # 점수 기준으로 영화 정렬
        recommended_movies = sorted(movie_scores.items(), key=lambda x: x[1], reverse=True)

        print(f"'{user_id}'를 위한 추천 영화 (상위 {num_recommendations}개):")
        return [movie for movie, score in recommended_movies[:num_recommendations]]

if __name__ == '__main__':
    # 모듈 테스트를 위한 코드
    from data_loader import load_data

    # 더미 데이터 로드
    ratings = load_data()

    if not ratings.empty:
        # 추천기 초기화
        recommender = MovieRecommender(ratings)

        # 특정 사용자에게 영화 추천
        test_user = 'user_1'
        recommendations = recommender.recommend_movies(test_user)
        print(recommendations)

        test_user_no_data = 'user_99'
        recommendations_no_data = recommender.recommend_movies(test_user_no_data)
        print(recommendations_no_data)
    else:
        print("평점 데이터가 비어 있어 추천 시스템을 초기화할 수 없습니다.")
