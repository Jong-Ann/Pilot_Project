package com.innotree.pilot.file;

import com.innotree.pilot.Response.Message;
import com.innotree.pilot.board.Board;
import com.innotree.pilot.board.BoardRepository;
import com.innotree.pilot.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@RestController
public class FileController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/delete/{imageName}/{boardId}")
    public ResponseEntity<?> deleteFile(@PathVariable("imageName") String imageName, @PathVariable("boardId") Integer boardId) throws IOException {
        FileResource download = new FileResource();
        Resource resource = null;

        resource = download.findResource(imageName);
        File resourceFile = resource.getFile();
        resourceFile.delete();
        Board getBoardPhoto = boardRepository.findById(boardId).get();
        getBoardPhoto.setImageNull();
        boardRepository.save(getBoardPhoto);
//        Message message = new Message();
//        message.setMessage("해당 하는 파일이 삭제되었습니다.");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/" + boardId));
        return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/download/{imageName}")
    public ResponseEntity<?> downloadFile(@PathVariable("imageName") String imageName) throws IOException {
        FileResource imagePath = new FileResource();
        Resource resource = null;
        try {
            resource = imagePath.findResource(imageName);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        if (resource == null) {
            return new ResponseEntity<>("해당하는 파일을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
        String conType = "application/octet-stream";
        String head = "attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(conType)).header(HttpHeaders.CONTENT_DISPOSITION, head).body(resource);
    }
}
//    @Autowired
//    private BoardService boardService;
//
//    @GetMapping("/download/{boardId}")
//    public void download(HttpServletResponse response,@PathVariable(name = "boardId") Integer id) throws Exception {
//        try {
//            String path = "C:\\Users\\SAMSUNG\\OneDrive\\바탕 화면\\Pilot_Project\\board-photos\\" + id+ "\\그리운 날에.jpg"; // 경로에 접근할 때 역슬래시('\') 사용
//            File file = new File(path);
//            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
//
//            FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
//            OutputStream out = response.getOutputStream();
//
//            int read = 0;
//            byte[] buffer = new byte[1024];
//            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
//                out.write(buffer, 0, read);
//            }
//
//        } catch (Exception e) {
//            throw new Exception("download error");
//        }
//    }


