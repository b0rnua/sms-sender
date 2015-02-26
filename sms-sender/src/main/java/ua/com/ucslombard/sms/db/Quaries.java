package ua.com.ucslombard.sms.db;

public class Quaries {
		
	public static final String SELECT_DATA = "SELECT DISTINCT contract_info.id, deliverer.telephone, filials.phoneNumber, "
			+ "(contract_info.date_end-Date()) AS days, filials.code_filial "
	 + "FROM (contract_info INNER JOIN deliverer ON (contract_info.id_deliverer = deliverer.id_deliverer) AND "
	 + "(contract_info.code_filial = deliverer.code_filial)) INNER JOIN filials ON contract_info.code_filial = filials.code_filial "
	 + "WHERE (((filials.phoneNumber)<>'') AND ((deliverer.telephone)<>'') AND "
	 + "((contract_info.date_end)=Date()-7 Or (contract_info.date_end)=Date() Or (contract_info.date_end)=Date()+3) AND "
	 + "((contract_info.con_status)=True) AND ((Len([deliverer].[telephone]))>=10 And (Len([deliverer].[telephone]))<=12) AND "
	 + "((contract_info.num_of_day)<>1))";
	
	private Quaries() {
		
	}

}
