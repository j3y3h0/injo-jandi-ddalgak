// dataProcessor.js
// 핵심 감성 분석 로직을 포함하고 있습니다.

// 긍정적인 감성을 나타내는 키워드 목록입니다.
const positiveKeywords = ['좋', '훌륭', '만족', '추천', '최고', '기대 이상', '고마워요', '편리'];
// 부정적인 감성을 나타내는 키워드 목록입니다.
const negativeKeywords = ['별로', '떨어지고', '실망', '사지 마', '돈 낭비', '후회', '최악', '고장'];

/**
 * 주어진 리뷰 텍스트의 감성을 분석합니다.
 * 키워드 매칭을 통해 긍정, 부정, 중립으로 분류합니다.
 * @param {string} reviewText - 분석할 상품 리뷰 텍스트.
 * @returns {string} - "긍정", "부정", 또는 "중립".
 */
function analyzeSentiment(reviewText) {
    let positiveScore = 0;
    let negativeScore = 0;

    // 텍스트를 소문자로 변환하여 검색의 일관성을 유지합니다.
    const lowerCaseReview = reviewText.toLowerCase();

    // 긍정 키워드 검색
    positiveKeywords.forEach(keyword => {
        if (lowerCaseReview.includes(keyword)) {
            positiveScore++;
        }
    });

    // 부정 키워드 검색
    negativeKeywords.forEach(keyword => {
        if (lowerCaseReview.includes(keyword)) {
            negativeScore++;
        }
    });

    if (positiveScore > negativeScore) {
        return "긍정";
    } else if (negativeScore > positiveScore) {
        return "부정";
    } else {
        return "중립"; // 긍정 및 부정 키워드가 없거나 같은 경우
    }
}

/**
 * 여러 상품 리뷰에 대해 감성 분석을 수행합니다.
 * @param {Array<Object>} reviews - 분석할 리뷰 객체들의 배열. 각 객체는 'id'와 'review' 속성을 가져야 합니다.
 * @returns {Array<Object>} - 각 리뷰와 그 감성 분석 결과를 포함하는 객체들의 배열.
 */
function processReviews(reviews) {
    return reviews.map(review => {
        const sentiment = analyzeSentiment(review.review);
        return {
            id: review.id,
            review: review.review,
            sentiment: sentiment
        };
    });
}

module.exports = {
    analyzeSentiment,
    processReviews
};
