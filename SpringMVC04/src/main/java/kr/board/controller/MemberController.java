package kr.board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	MemberMapper memberMapper;
	
	@RequestMapping("/memJoin.do")
	public String memJoin() {
		return "member/join";
	}
	
	@RequestMapping("/memRegisterCheck.do")
	public @ResponseBody int memRegisterCheck(@RequestParam("memID") String memID) {
	
		Member m = memberMapper.registerCheck(memID);
		if(m!=null || memID.equals("")) {
			return 0; // 이미존재, 입력이 안된 것 
		}
			
			
		return 1;
	}
	@RequestMapping("/memRegister.do")
	public String memRegister(Member m, RedirectAttributes rttr, HttpSession session, 
			String memPassword1, String memPassword2) {
		
		if(m.getMemID() == null || m.getMemID().equals("") || 
			memPassword1 == null || memPassword1.equals("") ||
			memPassword2 == null || memPassword2.equals("") ||
			m.getMemName() == null || m.getMemName().equals("") || 
			m.getMemAge() == 0 ||
			m.getMemGender() == null || m.getMemGender().equals("" ) ||
			m.getMemEmail() == null || m.getMemEmail().equals("")) {
			
			rttr.addFlashAttribute("msgType", "fail msg");
			rttr.addFlashAttribute("msg","모든 내용을 입력");
			return "redirect:/memJoin.do";
		}
		if(!memPassword1.equals(memPassword2)) {
			rttr.addFlashAttribute("msgType", "fail msg");
			rttr.addFlashAttribute("msg","비밀번호 불일치라고");
			return "redirect:/memJoin.do";
		}
		
		m.setMemProfile("");
		
		int result = memberMapper.register(m);
		if(result==1) {
			rttr.addFlashAttribute("msgType", "success msg");
			rttr.addFlashAttribute("msg","성공");
			
			session.setAttribute("mvo", m);
			
			return "redirect:/";
			
		}
		else {
			rttr.addFlashAttribute("msgType", "fail msg");
			rttr.addFlashAttribute("msg","이미 있대");
			return "redirect:/memJoin.do";
		}
		
	}
	
	@RequestMapping("/memLogout.do")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/memLoginForm.do")
	public String memLoginForm() {
		return "member/memLoginForm";
	}
	
	
	@RequestMapping("/memLogin.do")
	public String memLogin(Member m, RedirectAttributes rttr, HttpSession session) {
		if(m.getMemID() == null || m.getMemID().equals("") ||
				m.getMemPassword()==null || m.getMemPassword().equals("") 
				) {
			rttr.addFlashAttribute("msgType","실패 메세지");
			rttr.addFlashAttribute("msg","모든 내용을 입력해라");
			return "redirect:/memLoginForm.do";
		}
				
		Member mvo = memberMapper.memLogin(m);
		
		if(mvo!=null) {
			rttr.addFlashAttribute("msgType","성공 메세지");
			rttr.addFlashAttribute("msg","했음");
			session.setAttribute("mvo", mvo);
			return "redirect:/";
		}
		else {
			rttr.addFlashAttribute("msgType","실패 메세지");
			rttr.addFlashAttribute("msg","다시 입력해");
			return "redirect:/memLoginForm.do";
		}
		
		
	}
	
	@RequestMapping("/memUpdateForm.do")
	public String memUpdateForm() {
		return "member/memUpdateForm";
	}
	
	
	@RequestMapping("/memUpdate.do")
	public String memUpdate(Member m, RedirectAttributes rttr, String memPassword1, 
			String memPassword2, HttpSession session
			) {
		
		if(m.getMemID() == null || m.getMemID().equals("") || 
				memPassword1 == null || memPassword1.equals("") ||
				memPassword2 == null || memPassword2.equals("") ||
				m.getMemName() == null || m.getMemName().equals("") || 
				m.getMemAge() == 0 ||
				m.getMemGender() == null || m.getMemGender().equals("" ) ||
				m.getMemEmail() == null || m.getMemEmail().equals("")) {
				
				rttr.addFlashAttribute("msgType", "fail msg");
				rttr.addFlashAttribute("msg","모든 내용을 입력");
				return "redirect:/memUpdateForm.do";
			}
			if(!memPassword1.equals(memPassword2)) {
				rttr.addFlashAttribute("msgType", "fail msg");
				rttr.addFlashAttribute("msg","비밀번호 불일치라고");
				return "redirect:/memUpdateForm.do";
			}
			
			
			int result = memberMapper.memUpdate(m);
			if(result==1) {
				rttr.addFlashAttribute("msgType", "success msg");
				rttr.addFlashAttribute("msg","수정성공");
				
				Member mvo = memberMapper.getMember(m.getMemID());
				
				session.setAttribute("mvo", m);
				
				return "redirect:/";
				
			}
			else {
				rttr.addFlashAttribute("msgType", "fail msg");
				rttr.addFlashAttribute("msg","이미 있대");
				return "redirect:/memUpdateForm.do";
			}
	}
	
	
	@RequestMapping("/memImageForm.do")
	public String memImageForm() {
		return "member/memImageForm";
	}
	
	
	@RequestMapping("/memImageUpdate.do")
	public String memImageUpdate(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		
		MultipartRequest multi = null;
		int fileMaxSize=10*1024*1024;
		String savePath = request.getRealPath("resources/upload");
		
		System.out.println("savePath : "+savePath);
		
		try {
			
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		}
		catch(Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg","파일 크기는 10메가 이하");
			return "redirect:/memImageForm.do";
		}
		
		String memID = multi.getParameter("memID");
		String newProfile = "";
		
		File file = multi.getFile("memProfile");
		
		if(file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			System.out.println("ext : "+ext);
			ext = ext.toUpperCase();
			System.out.println("ext : "+ext);

			if(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG") || ext.equals("BMP")) {
				
				String oldProfile = memberMapper.getMember(memID).getMemProfile();
				File oldFile = new File(savePath+"/"+oldProfile);
				
				if(oldFile.exists()) {
					oldFile.delete();
				}
				
				newProfile = file.getName();
				
			}
			else {
				if(file.exists()) {
					file.delete();
				}
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg","확장자");
				return "redirect:/memImageForm.do";
			}
		}
		
		Member mvo = new Member();
		mvo.setMemID(memID);
		mvo.setMemProfile(newProfile);
		
		memberMapper.memProfileUpdate(mvo);
		Member m = memberMapper.getMember(memID);
		
		session.setAttribute("mvo", m);
		rttr.addFlashAttribute("msgType", "성공 메세지");
		rttr.addFlashAttribute("msg","이미지 변경이 성공했습니다.");
		
		
		return "redirect:/";
	}
}
