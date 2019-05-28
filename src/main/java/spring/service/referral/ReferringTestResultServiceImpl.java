package spring.service.referral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.referral.dao.ReferringTestResultDAO;
import us.mn.state.health.lims.referral.valueholder.ReferringTestResult;

@Service
public class ReferringTestResultServiceImpl extends BaseObjectServiceImpl<ReferringTestResult> implements ReferringTestResultService {
	@Autowired
	protected ReferringTestResultDAO baseObjectDAO;

	ReferringTestResultServiceImpl() {
		super(ReferringTestResult.class);
	}

	@Override
	protected ReferringTestResultDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}
}