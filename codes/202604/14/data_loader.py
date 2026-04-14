# data_loader.py

import pandas as pd
import numpy as np

def load_data():
    """
    더미 사용자-영화 평점 데이터를 로드합니다.
    실제 프로젝트에서는 데이터베이스나 파일에서 데이터를 불러옵니다.
    """
    print("더미 평점 데이터를 로드합니다.")
    # 사용자 ID
    users = [f'user_{i}' for i in range(1, 11)]
    # 영화 ID
    movies = [f'movie_{i}' for i in range(1, 16)]

    # 더미 평점 데이터 생성 (0 = 평점 없음)
    data = []
    for user in users:
        for movie in movies:
            # 50% 확률로 평점 부여
            if np.random.rand() > 0.5:
                rating = np.random.randint(1, 6) # 1점에서 5점 사이의 평점
                data.append({'user_id': user, 'movie_id': movie, 'rating': rating})

    df = pd.DataFrame(data)
    print(f"로드된 데이터 샘플:
{df.head()}")
    return df

if __name__ == '__main__':
    # 모듈 테스트를 위한 코드
    ratings_df = load_data()
    print(f"
총 평점 데이터 수: {len(ratings_df)}")
    print(f"고유 사용자 수: {ratings_df['user_id'].nunique()}")
    print(f"고유 영화 수: {ratings_df['movie_id'].nunique()}")
