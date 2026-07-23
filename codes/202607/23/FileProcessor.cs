using System.Collections.Generic;
using System.IO;

namespace WordFrequencyCounter
{
    public static class FileProcessor
    {
        /// <summary>
        /// 지정된 파일 경로에서 모든 줄을 읽어 IEnumerable<string> 형태로 반환합니다.
        /// </summary>
        /// <param name="filePath">읽을 파일의 경로입니다.</param>
        /// <returns>파일의 각 줄을 포함하는 문자열 시퀀스입니다.</returns>
        public static IEnumerable<string> ReadLines(string filePath)
        {
            // File.ReadLines는 파일을 한 번에 모두 메모리에 로드하지 않고,
            // 한 줄씩 읽어오므로 대용량 파일 처리에 효율적입니다.
            return File.ReadLines(filePath);
        }
    }
}