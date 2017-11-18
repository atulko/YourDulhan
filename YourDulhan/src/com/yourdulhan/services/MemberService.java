package com.yourdulhan.services;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yourdulhan.daoImpl.MemberImpl;
import com.yourdulhan.pojo.Member;
import com.yourdulhan.result.MemberResponse;
import com.google.gson.Gson;



@Path("/MemberService")
public class MemberService 
{
	
	MemberImpl memberImpl=null;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addMember")
	@Produces("application/json")
	
	public MemberResponse addMember(String memberData)
	{
		MemberResponse response=null;
		memberImpl = new MemberImpl();
		try {
			Gson gson=new Gson();
			Member member=gson.fromJson(memberData,Member.class);
			boolean result=memberImpl.addMember(member);
			if(result)
			{
					response=new MemberResponse();
					response.setReason("Member saved Successfully");
					response.setResult(true);
					response.setStatus("success");
					response.setStatusCode(200);
					
			}else
			{
				response=new MemberResponse();
				response.setReason("Failed to  saved Successfully");
				response.setResult(false);
				response.setStatus("Failed");
				response.setStatusCode(0);
			}			
		} catch (Exception e) {
			response=new MemberResponse();
			response.setReason("Failed to  saved Successfully:"+e.getMessage());
			response.setResult(false);
			response.setStatus("Failed");
			response.setStatusCode(0);
		}
	
	return response;
}
	
	@GET
	//@RolesAllowed("ADMIN")
	@Path("/getMemberlist")
	@Produces("application/json")
	public MemberResponse getMemberList() {
		MemberResponse response=null;
		memberImpl = new MemberImpl();
		

		try {
			List<Member> memberList = memberImpl.getMemberList();


			if(memberList != null && memberList.size() > 0){
				response = new MemberResponse();
				response.setReason("Data Present");
				response.setStatus("success");
				response.setListOfMember(memberList);
				response.setStatusCode(200);
				response.setResult(true);

			}else{
				response = new MemberResponse();
				response.setReason("No data Found");
				response.setStatus("failed");
				response.setListOfMember(null);
				response.setStatusCode(0);
				response.setResult(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = new MemberResponse();
			response.setReason("No data Found :Exception ==>  " + e.getMessage());
			response.setStatus("failed");
			response.setResult(false);
			response.setListOfMember(null);
		}
		return response;
	}
	@GET
	//@RolesAllowed("ADMIN")
	//@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getsMemberdetailsById/{societyId}")
	@Produces("application/json")
	public MemberResponse getsMemberdetailsById(@PathParam("memberId") int memberId) {
		MemberResponse response=null;
		memberImpl = new MemberImpl();
		Member member = null;
		System.out.println(" Details of Json : "+memberId);
		try {

			if(memberId !=0 && memberId > 0){
				member = memberImpl.getMemberDetailsById(memberId);
			}


			if(member != null){
				response = new MemberResponse();
				response.setReason("Data Present");
				response.setStatus("success");
				response.setMember(member);
				response.setResult(true);
			}else{
				response = new MemberResponse();
				response.setReason("No data Found");
				response.setStatus("failed");
				response.setMember(null);
				response.setResult(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = new MemberResponse();
			response.setReason("No data Found :Exception ==>  " + e.getMessage());
			response.setStatus("failed");
			response.setMember(null);
			response.setResult(false);
		}

		return response;

	}

}
