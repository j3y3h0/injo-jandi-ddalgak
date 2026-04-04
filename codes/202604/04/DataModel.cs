using System;
using Newtonsoft.Json;

namespace JsonExample
{
    /// <summary>
    /// 예시로 사용할 사용자 데이터 모델을 정의합니다.
    /// 이 모델은 JSON 직렬화 및 역직렬화에 사용됩니다.
    /// </summary>
    public class UserProfile
    {
        /// <summary>
        /// 사용자의 고유 ID입니다.
        /// </summary>
        [JsonProperty("id")] // JSON 필드 이름을 'id'로 지정
        public int Id { get; set; }

        /// <summary>
        /// 사용자의 이름입니다.
        /// </summary>
        [JsonProperty("name")] // JSON 필드 이름을 'name'으로 지정
        public string Name { get; set; }

        /// <summary>
        /// 사용자의 이메일 주소입니다.
        /// </summary>
        [JsonProperty("email")] // JSON 필드 이름을 'email'로 지정
        public string Email { get; set; }

        /// <summary>
        /// 사용자가 활성 상태인지 여부를 나타냅니다.
        /// </summary>
        [JsonProperty("isActive")] // JSON 필드 이름을 'isActive'로 지정
        public bool IsActive { get; set; }

        /// <summary>
        /// 사용자 프로필이 생성된 날짜입니다.
        /// </summary>
        [JsonProperty("createdAt")] // JSON 필드 이름을 'createdAt'으로 지정
        public DateTime CreatedAt { get; set; }

        public UserProfile(int id, string name, string email, bool isActive, DateTime createdAt)
        {
            Id = id;
            Name = name;
            Email = email;
            IsActive = isActive;
            CreatedAt = createdAt;
        }

        public UserProfile() { } // 역직렬화를 위한 기본 생성자
    }
}
