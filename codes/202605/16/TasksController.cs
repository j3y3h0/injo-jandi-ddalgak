// TasksController.cs

using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using TaskManagerApi.Models; // TaskItem 모델을 사용하기 위해 필요하다.

namespace TaskManagerApi.Controllers
{
    // API 컨트롤러임을 나타내는 속성이다.
    [Route("api/[controller]")]
    [ApiController]
    public class TasksController : ControllerBase
    {
        // 태스크 항목들을 저장하는 메모리 내 컬렉션이다. (간단한 예제를 위해 사용된다.)
        private static List<TaskItem> _tasks = new List<TaskItem>
        {
            new TaskItem { Id = 1, Title = "점심 식사 준비", IsComplete = false },
            new TaskItem { Id = 2, Title = "운동하기", IsComplete = true }
        };
        private static long _nextId = 3; // 다음 태스크에 할당될 ID이다.

        // 모든 태스크 항목을 비동기적으로 가져온다. (GET api/tasks)
        [HttpGet]
        public ActionResult<IEnumerable<TaskItem>> GetTaskItems()
        {
            return Ok(_tasks);
        }

        // 특정 ID를 가진 태스크 항목을 비동기적으로 가져온다. (GET api/tasks/{id})
        [HttpGet("{id}")]
        public ActionResult<TaskItem> GetTaskItem(long id)
        {
            var taskItem = _tasks.FirstOrDefault(t => t.Id == id);
            if (taskItem == null)
            {
                // 해당 ID의 태스크가 없으면 404 Not Found를 반환한다.
                return NotFound();
            }
            return Ok(taskItem);
        }

        // 새로운 태스크 항목을 생성한다. (POST api/tasks)
        [HttpPost]
        public ActionResult<TaskItem> CreateTaskItem(TaskItem taskItem)
        {
            // 새로운 태스크에 ID를 할당하고 컬렉션에 추가한다.
            taskItem.Id = _nextId++;
            _tasks.Add(taskItem);
            // 생성된 태스크와 함께 201 Created 응답을 반환한다.
            return CreatedAtAction(nameof(GetTaskItem), new { id = taskItem.Id }, taskItem);
        }

        // 기존 태스크 항목을 업데이트한다. (PUT api/tasks/{id})
        [HttpPut("{id}")]
        public IActionResult UpdateTaskItem(long id, TaskItem taskItem)
        {
            if (id != taskItem.Id)
            {
                // 요청된 ID와 태스크 객체의 ID가 일치하지 않으면 Bad Request를 반환한다.
                return BadRequest();
            }

            var existingTask = _tasks.FirstOrDefault(t => t.Id == id);
            if (existingTask == null)
            {
                // 해당 ID의 태스크가 없으면 404 Not Found를 반환한다.
                return NotFound();
            }

            // 기존 태스크의 속성을 업데이트한다.
            existingTask.Title = taskItem.Title;
            existingTask.IsComplete = taskItem.IsComplete;

            // 204 No Content 응답을 반환한다.
            return NoContent();
        }

        // 특정 ID를 가진 태스크 항목을 삭제한다. (DELETE api/tasks/{id})
        [HttpDelete("{id}")]
        public IActionResult DeleteTaskItem(long id)
        {
            var taskItem = _tasks.FirstOrDefault(t => t.Id == id);
            if (taskItem == null)
            {
                // 해당 ID의 태스크가 없으면 404 Not Found를 반환한다.
                return NotFound();
            }

            // 컬렉션에서 태스크를 제거한다.
            _tasks.Remove(taskItem);

            // 204 No Content 응답을 반환한다.
            return NoContent();
        }
    }
}
