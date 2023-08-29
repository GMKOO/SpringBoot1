package com.karenmm.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.mail.EmailException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private Util util;

	@Autowired
	private AdminService adminService;

	@GetMapping(value = { "/", "/admin" })
	public String adminIndex() {

		return "admin/index";
	}

	@GetMapping(value = { "/main", "/home" })
	public String adminmain() {

		return "admin/main";
	}

	@PostMapping("/login")
	public String adminLogin(@RequestParam Map<String, Object> map, HttpSession session) {

		System.out.println(map);

		Map<String, Object> result = adminService.adminLogin(map);

		System.out.println(result);
		System.out.println(result.get("count"));

		// if( Integer.parseInt(String.valueOf(map.get("m_grade"))) < 5) {
		if (util.obtoint(result.get("count")) == 1 && (util.obtoint(result.get("m_grade")) > 5)) {

			session.setAttribute("mid", map.get("id"));
			session.setAttribute("mname", map.get("m_name"));
			session.setAttribute("mgrade", map.get("m_grade"));

			return "redirect:/admin/main";
		} else {
			return "redirect:/admin/admin?error=login";
		}
	}

	@GetMapping("/board")
	public String adminboard() {

		return "admin/board";
	}

	@GetMapping("/main1")
	public String adminmain1() {

		return "admin/main1";
	}

	@GetMapping("/notice")
	public String notice(Model model) {

		List<Map<String, Object>> list = adminService.list();

		// 1.데이터베이스까지 연결하기
		// 2.데이터불러오기
		// 3.데이터 jsp로 보내기
		model.addAttribute("list", list);

		return "admin/notice";
	}

	@PostMapping("/noticeWrite")
	public String noticeWrite(@RequestParam("upFile") MultipartFile upfile, @RequestParam Map<String, Object> map) {
		System.out.println(map);

		// 2023-08-22 요구사항확인
		//

		if (!upfile.isEmpty()) {

			// 저장할 경로명 뽑기 request뽑기
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			// String타입의 경로를 file형태로 바꿔주겠습니다.
			String path = request.getServletContext().getRealPath("/upload");

			// File filePath = new File(path);

			// 진짜로 파일 업로드하기 경로 + 저장할 파일명
			// 중복이 발생할 수 있기 때문에... 파일명 +날짜+ID.파일확장자
			// uuid +파일명 + .확장자
			// 아이디 + 날짜 +파일명 . 확장자
			UUID uuid = UUID.randomUUID();

			// 날짜 뽑기 SimpleDateFormat
			// Date date = new Date();
			LocalDateTime ldt = LocalDateTime.now();
			String format = ldt.format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss"));
			// SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
			// String dataTime = sdf.format(date);
			// String realFileName = dataTime.toString()+uuid.toString() +
			// upfile.getOriginalFilename();
			// String realFileName = dataTime.toString() + upfile.getOriginalFilename();
			// String realFileName = format.toString() + upfile.getOriginalFilename();
			// 날짜 +uuid +실제 파일명으로 사용하겠습니다.
			// String realFileName = format.toString() +uuid.toString()+
			// upfile.getOriginalFilename();
			String realFileName = format + uuid.toString() + upfile.getOriginalFilename();

			// File newFileName = new File(path+"/"+upfile.getOriginalFilename());
			// File newFileName = new File(path,upfile.getOriginalFilename());
			File newFileName = new File(path, realFileName);
			// 이제 파일 올립니다.
			try {
				upfile.transferTo(newFileName);

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("저장 끝.");
			/*
			 * transferto를 사용하거나 FileCopyUtils를 사용하면된다 //FileCopyUtils를 사용하기 위해서는 오리지널 파일을
			 * byte[]로 만들어야 합니다. try { FileCopyUtils.copy(upfile.getBytes(), newFileName); }
			 * catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 * 
			 */
			System.out.println("실제경로" + path);
			map.put("mno", 4);
			// up파일 정보보기
			System.out.println(upfile.getOriginalFilename());
			System.out.println(upfile.getSize());
			System.out.println(upfile.getContentType());

			map.put("upFile", upfile.getOriginalFilename());
			map.put("realFile", realFileName);
			adminService.noticeWrite(map);
			System.out.println(realFileName);
			System.out.println(upfile.getOriginalFilename());
		}
		return "redirect:/admin/notice";

	}

	@GetMapping("/mail")
	public String mail() {
		return "admin/mail";

	}

	@PostMapping("/mail")
	public String mail(@RequestParam Map<String, Object> map) throws EmailException {

		// System.out.println(map);
		// return "forward:/mail";
		// util.simpleMailSender(map);
		util.HtmlMailSender(map);

		return "admin/mail";
	}

	@ResponseBody
	@PostMapping("/noticeDetail")
	public String noticeDetail(@RequestParam("nno") int nno, Model model) {

		JSONObject json = new JSONObject();
		String result = adminService.noticeDetail(nno);

		json.put("content", result);

		/*
		 * System.out.println();
		 * 
		 * ObjectNode json = JsonNodeFactory.instance.objectNode(); Map<String, Object>
		 * maaaap = new HashMap<String, Object>(); maaaap.put("bno",123);
		 * maaaap.put("btitle",1234);
		 * 
		 * ObjectMapper jsonMap = new ObjectMapper();
		 * 
		 * try { json.put("map", jsonMap.writeValueAsString(maaaap)); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * json.put("content", adminService.noticeDetail(nno));
		 * 
		 */

		return json.toString();
	}

	// noticeHide
	@ResponseBody
	@PostMapping("/noticeHide")
	public String noticeHide(@RequestParam("nno") int nno) {

		JSONObject json = new JSONObject();
		// String result = adminService.noticeHide(nno);

		// ObjectNode json1 = JsonNodeFactory.instance.objectNode();
		int result = adminService.noticeHide(nno);

		json.put("result", result);

		System.out.println(result);
		System.out.println(json.toString());

		return json.toString();
	}

	@RequestMapping(value = "/multiboard", method = RequestMethod.GET)
	public String multiBoard(Model model) {

		List<Map<String, Object>> map = adminService.setupBoardList();

		model.addAttribute("view", map);

		return "admin/multiboard";
	}

	@PostMapping("/member")
	public String member(Model model) {

		List<Map<String, Object>> list = adminService.member();

		return "";
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ModelAndView member() {
		ModelAndView mv = new ModelAndView("admin/member");
		mv.addObject("memberList", adminService.memberlist());
		System.out.println(mv);
		return mv;
	}

	@PostMapping("/multiboard")
	public String multiBoard1(@RequestParam Map<String, Object> map, Model model) {

		int result = adminService.multiboard1(map);

		model.addAttribute("create", result);

		return "redirect:/admin/multiboard";
	}

	@RequestMapping(value = "/gradeChange", method = RequestMethod.GET)
	public String gradeChange(@RequestParam Map<String, String> map) {
		int result = adminService.gradeChange(map);

		return "redirect:/admin/member";

	}

	@GetMapping("/post")
	public String post(Model model, @RequestParam(name = "cate", required = false, defaultValue = "0") int cate,
			@RequestParam Map<String, Object> map) {

		System.out.println("검색:" + map);
		System.out.println("cate:" + cate);

		if (!(map.containsKey("cate")) || map.get("cate").equals(null) || map.get("cate").equals("")) {
			map.put("cate", 0);
		}
		// 게시판 번호가 들어 올수 있습니다. 추후처리
		// 게시판 관리번호를 다 불러옵니다.
		// 게시글을 다 불러 옵니다.
		List<Map<String, Object>> boardList = adminService.boardList();
		model.addAttribute("boardList", boardList);

		List<Map<String, Object>> list = adminService.post(map);
		model.addAttribute("list", list);
		return "/admin/post";
	}
	
	@ResponseBody
	@GetMapping("/detail")
	public String detail(@RequestParam("mbno") int mbno) {
		
		String content = adminService.content(mbno);
		System.out.println(content);
		JSONObject json = new JSONObject();
		json.put("content", content);
		return json.toString();
		
	}
	
	@GetMapping("/corona")
	public String corona(Model model) throws IOException {
		
	
		        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1790387/covid19CurrentStatusKorea/covid19CurrentStatusKoreaJason"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=UZlWnCy7oD5Wjf1tLwf09EAWWC%2BPT%2FGL0KVrjKWMO9MLeTflNf04r8Gf3m0tv%2FA0YUN8IUJaGaUNWf8WAY4sKA%3D%3D"); /*Service Key*/
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        
		       
		        model.addAttribute("corona", sb);
		        
		        //String to Json
		        ObjectMapper objectMapper = new ObjectMapper();
		        JsonNode jsonN = objectMapper.readTree(sb.toString());
		        
		        System.out.println(jsonN);
		        System.out.println(jsonN.get("response").get("result").get(0));
		        
		        //Json to map
		        Map<String,Object> result = objectMapper.readValue((jsonN.get("response").get("result").get(0)).toString(),
		        		new TypeReference<Map<String,Object>>(){}
						);
		        
		        model.addAttribute("result", result);
		        
		       // JSONObject jsonData = new JSONObject();
		       // jsonData.put("problem", sb);
		        //JSONObject json = (JSONObject)new JSONParser().parse(jsonData);
		       // json.put("result", sb);
		    
	
		    
	
		        
		        return "/admin/corona";   
	}
/*		
	@GetMapping("/corona")
	   public String corona(Model model) throws IOException {
	      StringBuilder urlBuilder = new StringBuilder(
	            "http://apis.data.go.kr/1790387/covid19CurrentStatusKorea/covid19CurrentStatusKoreaJason"); //url
	      urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=X2%2BL9ngA9U%2BUDcmJaEl8ESe3WiuXfcG5rlbqRFefc4sZqoZiZtC1z3gPPtX782lvE1bLYQHxIt%2Fy981RH%2FkJqA%3D%3D"); //Service Key 
	      URL url = new URL(urlBuilder.toString());
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      conn.setRequestProperty("Content-type", "application/json");
	      System.out.println("Response code: " + conn.getResponseCode());
	      BufferedReader rd;
	      if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      } else {
	         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	      }
	      StringBuilder sb = new StringBuilder();
	      String line;
	      while ((line = rd.readLine()) != null) {
	         sb.append(line);
	      }
	      rd.close();
	      conn.disconnect();
	      //System.out.println(sb.toString());
	      model.addAttribute("corona", sb.toString());
	      
	      
	      //String to Json
	      ObjectMapper objectMapper = new ObjectMapper();
	      JsonNode jsonN = objectMapper.readTree(sb.toString());
	      //JsonNode jsonV = objectMapper.readValue(sb.toString(), JsonNode.class);
	      
	      System.out.println(jsonN.get("response").get("result").get(0));
	      
	      //Json to map
	      Map<String, Object> result = objectMapper.readValue(
	            (jsonN.get("response").get("result").get(0)).toString()
	            , new TypeReference<Map<String, Object>>(){}
	            );
	      
	      System.out.println(result);
	      model.addAttribute("result", result);
	      return "/admin/corona";
	   }
	
	*/	
	
	@GetMapping("/air2")
	public String air2(Model model) throws Exception {
		
		   StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnStatsSvc/getMsrstnAcctoRDyrg"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=UZlWnCy7oD5Wjf1tLwf09EAWWC%2BPT%2FGL0KVrjKWMO9MLeTflNf04r8Gf3m0tv%2FA0YUN8IUJaGaUNWf8WAY4sKA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&returnType=xml"); /*xml 또는 json*/
	       // urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&numOfRows=100"); /*한 페이지 결과 수*/
	       // urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&pageNo=1"); /*페이지번호*/
	        urlBuilder.append("&inqBginDt=20230801"); /*조회시작일자*/
	       //urlBuilder.append("&" + URLEncoder.encode("inqEndDt","UTF-8") + "=" + URLEncoder.encode("20230828", "UTF-8")); /*조회종료일자*/
	       urlBuilder.append("&inqEndDt=20230828"); /*조회종료일자*/
	        //urlBuilder.append("&" + URLEncoder.encode("msrstnName","UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); /*측정소명*/
	        urlBuilder.append("&msrstnName=" + URLEncoder.encode("강남구", "UTF-8")); /*측정소명*/
	       URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        model.addAttribute("result", sb);
	    	
	        
	        
	        //String to xml
	       // DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        //DocumentBuilder builder =factory.newDocumentBuilder();
	        //Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
	        
	        
			return "/admin/air2";
	    }
	
	@GetMapping("/air")
	   public String air(Model model) throws Exception {
	      // String to xml
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder builder = factory.newDocumentBuilder();
	      Document document = builder.parse("c:\\temp\\air.xml");

	      //document.getDocumentElement().normalize();
	      System.out.println(document.getDocumentElement().getNodeName());
	      
	      NodeList list = document.getElementsByTagName("item");
	         //System.out.println("item length = " + list.getLength());
	         //System.out.println(list.toString());
	         ArrayList<Map<String, Object>> coronaList = new ArrayList<Map<String,Object>>();
	         for (int i = list.getLength() - 1; i >= 0; i--) {
	            NodeList childList = list.item(i).getChildNodes();
	            
	            Map<String, Object> value = new HashMap<String, Object>();
	            for (int j = 0; j < childList.getLength(); j++) {
	               Node node = childList.item(j);
	               if (node.getNodeType() == Node.ELEMENT_NODE) { 
	                  //System.out.println(node.getNodeName());
	                  //System.out.println(node.getTextContent());
	                  value.put(node.getNodeName(), node.getTextContent());
	               }
	            }
	            coronaList.add(value);
	         }
	         System.out.println("xml : " + coronaList);
	         model.addAttribute("list", coronaList);

	      return "/admin/air";
	   }
		
	
		
		
		
		
	}
	


