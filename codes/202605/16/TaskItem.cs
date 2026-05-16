// TaskItem.cs

namespace TaskManagerApi.Models
{
    // 태스크 항목을 나타내는 모델 클래스이다.
    public class TaskItem
    {
        // 태스크의 고유 식별자이다.
        public long Id { get; set; }

        // 태스크의 제목 또는 내용이다.
        public string Title { get; set; }

        // 태스크의 완료 여부를 나타낸다. (참: 완료됨, 거짓: 미완료)
        public bool IsComplete { get; set; }
    }
}
