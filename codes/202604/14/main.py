# main.py

from data_loader import load_data
from recommender import MovieRecommender

def main():
    """
    영화 추천 시스템의 메인 실행 함수입니다.
    데이터를 로드하고, 추천기를 초기화하며, 사용자에게 영화를 추천합니다.
    """
    print("영화 추천 시스템을 시작합니다.")

    # 1. 데이터 로드
    ratings_df = load_data()

    if ratings_df.empty:
        print("로드된 평점 데이터가 없습니다. 프로그램을 종료합니다.")
        return

    # 2. 영화 추천기 초기화
    recommender = MovieRecommender(ratings_df)

    # 3. 특정 사용자에게 영화 추천
    # 예시: 'user_1'에게 5개의 영화를 추천
    user_to_recommend = 'user_1'
    recommendations = recommender.recommend_movies(user_to_recommend, num_recommendations=5)

    if recommendations:
        print(f"
'{user_to_recommend}'님께 추천하는 영화 목록: {recommendations}")
    else:
        print(f"
'{user_to_recommend}'님께 추천할 영화를 찾을 수 없습니다.")

    # 다른 사용자에게도 추천 시도
    user_to_recommend_2 = 'user_5'
    recommendations_2 = recommender.recommend_movies(user_to_recommend_2, num_recommendations=3)
    if recommendations_2:
        print(f"
'{user_to_recommend_2}'님께 추천하는 영화 목록: {recommendations_2}")
    else:
        print(f"
'{user_to_recommend_2}'님께 추천할 영화를 찾을 수 없습니다.")


if __name__ == '__main__':
    main()
