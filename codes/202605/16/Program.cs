// Program.cs

using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

// 웹 애플리케이션 빌더를 생성한다.
var builder = WebApplication.CreateBuilder(args);

// 컨트롤러를 서비스 컨테이너에 추가한다.
// 이로써 ASP.NET Core가 API 컨트롤러들을 찾고 관리할 수 있게 된다.
builder.Services.AddControllers();

// Swagger/OpenAPI 기능을 추가하여 API 문서를 자동 생성한다.
// 개발 환경에서 API 엔드포인트를 쉽게 테스트하고 탐색할 수 있도록 돕는다.
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// 웹 애플리케이션을 빌드한다.
var app = builder.Build();

// 개발 환경일 경우 Swagger UI를 활성화한다.
// Swagger UI는 API 문서를 웹 인터페이스로 보여준다.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

// HTTPS 리디렉션을 사용하도록 설정한다.
// 보안을 강화하기 위해 HTTP 요청을 HTTPS로 전환한다.
app.UseHttpsRedirection();

// 라우팅 기능을 활성화한다.
// HTTP 요청을 적절한 컨트롤러 액션으로 매핑한다.
app.UseRouting();

// 권한 부여 미들웨어를 사용하도록 설정한다.
// 인증된 사용자에게 특정 리소스에 대한 접근 권한을 부여하는 역할을 한다.
// (현재 예제에서는 별도의 인증/권한 부여 로직이 없으므로 단순 활성화만 한다.)
app.UseAuthorization();

// 컨트롤러 엔드포인트를 매핑한다.
// 'TasksController'와 같은 API 컨트롤러가 HTTP 요청을 처리할 수 있도록 설정한다.
app.MapControllers();

// 웹 애플리케이션을 실행하여 요청을 수신 대기한다.
app.Run();
