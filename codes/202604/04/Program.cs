using System;
using Newtonsoft.Json; // Newtonsoft.Json 사용을 위해 필요

namespace JsonExample
{
    /// <summary>
    /// 프로그램의 메인 진입점 클래스입니다.
    /// UserProfile 객체를 JSON으로 직렬화하고, 다시 역직렬화하는 과정을 보여줍니다.
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("C# JSON 직렬화 및 역직렬화 예제 시작!");

            // 1. 직렬화할 UserProfile 객체 생성
            UserProfile user = new UserProfile(
                id: 1,
                name: "홍길동",
                email: "hong.gildong@example.com",
                isActive: true,
                createdAt: DateTime.UtcNow
            );

            Console.WriteLine("
--- 원본 UserProfile 객체 ---");
            Console.WriteLine($"ID: {user.Id}");
            Console.WriteLine($"이름: {user.Name}");
            Console.WriteLine($"이메일: {user.Email}");
            Console.WriteLine($"활성 상태: {user.IsActive}");
            Console.WriteLine($"생성일: {user.CreatedAt}");

            // 2. UserProfile 객체를 JSON 문자열로 직렬화
            Console.WriteLine("
--- 객체를 JSON 문자열로 직렬화 ---");
            string jsonString = JsonProcessor.SerializeObject(user);
            Console.WriteLine(jsonString);

            // 3. JSON 문자열을 다시 UserProfile 객체로 역직렬화
            Console.WriteLine("
--- JSON 문자열을 객체로 역직렬화 ---");
            try
            {
                UserProfile deserializedUser = JsonProcessor.DeserializeObject<UserProfile>(jsonString);

                Console.WriteLine("
--- 역직렬화된 UserProfile 객체 ---");
                Console.WriteLine($"ID: {deserializedUser.Id}");
                Console.WriteLine($"이름: {deserializedUser.Name}");
                Console.WriteLine($"이메일: {deserializedUser.Email}");
                Console.WriteLine($"활성 상태: {deserializedUser.IsActive}");
                Console.WriteLine($"생성일: {deserializedUser.CreatedAt}");

                // 원본 객체와 역직렬화된 객체의 내용 비교
                Console.WriteLine("
--- 원본과 역직렬화된 객체 비교 ---");
                bool isEqual = user.Id == deserializedUser.Id &&
                               user.Name == deserializedUser.Name &&
                               user.Email == deserializedUser.Email &&
                               user.IsActive == deserializedUser.IsActive &&
                               user.CreatedAt == deserializedUser.CreatedAt; // DateTime 비교는 좀 더 복잡할 수 있으나 단순 비교

                Console.WriteLine($"원본 객체와 역직렬화된 객체는 {(isEqual ? "동일합니다." : "다릅니다.")}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"역직렬화 중 오류가 발생했습니다: {ex.Message}");
            }

            Console.WriteLine("
C# JSON 직렬화 및 역직렬화 예제 종료!");
        }
    }
}
