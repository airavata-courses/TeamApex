package edu.sga.apex.rest.util;

import java.util.List;

import edu.sga.apex.bean.InputFileBean;
import edu.sga.apex.bean.JobBean;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.entity.AppInput;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Role;
import edu.sga.apex.rest.jaxb.Application;
import edu.sga.apex.rest.jaxb.ApplicationInput;
import edu.sga.apex.rest.jaxb.ApplicationListResponse;
import edu.sga.apex.rest.jaxb.ExperimentListResponse;
import edu.sga.apex.rest.jaxb.InputFile;
import edu.sga.apex.rest.jaxb.JobResponse;
import edu.sga.apex.rest.jaxb.Machine;
import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.jaxb.SubmitJobRequest;
import edu.sga.apex.rest.jaxb.User;
import edu.sga.apex.util.UserDAOUtil;
import edu.sga.apex.util.UserRoles;

/**
 * The Class JAXBManager.
 * 
 * @author Gourav Shenoy
 */
public class JAXBManager {

	/** The factory. */
	private static ObjectFactory factory = new ObjectFactory();
	
	/**
	 * Gets the submit job request bean.
	 *
	 * @param request            the request
	 * @return the submit job request bean
	 * @throws Exception the exception
	 */
	public static SubmitJobRequestBean getSubmitJobRequestBean(
			SubmitJobRequest request, String userName) throws Exception {
		
		SubmitJobRequestBean bean = new SubmitJobRequestBean();
		
		/* throw error if required field not specified */
		if (request != null) {
			/* throw error if required field not specified */
			if (request.getJobName() == null) {
				throw new Exception(String.format(Constants.MISSING_FIELD_EXCEPTION, "jobName"));
			}
			bean.setJobName(request.getJobName());
			
			/* throw error if required field not specified */
			if (request.getApplicationID() == null) {
				throw new Exception(String.format(Constants.MISSING_FIELD_EXCEPTION, "applicationID"));
			}
			bean.setApplicationID(request.getApplicationID());
			
			/* throw error if required field not specified */
			if (request.getMachineID() == null) {
				throw new Exception(String.format(Constants.MISSING_FIELD_EXCEPTION, "machineID"));
			}
			bean.setMachineID(request.getMachineID());

			/* throw error if required field not specified */
			if (request.getEmailId() == null) {
				throw new Exception(String.format(Constants.MISSING_FIELD_EXCEPTION, "emailId"));
			}
			bean.setEmailId(request.getEmailId());

			/* if walltime not provided, use default */
			if (request.getWallTime() == null) {
				bean.setWallTime(Constants.DEFAULT_WALLTIME);
			} else {
				bean.setWallTime(request.getWallTime());
			}

			/* if num of nodes not provided, use default */
			if (request.getNumNodes() == null) {
				bean.setNumNodes(Constants.DEFAULT_NUM_NODES);
			} else {
				bean.setNumNodes(request.getNumNodes());
			}

			/* if num of processors not provided, use default */
			if (request.getNumProcessors() == null) {
				bean.setNumProcessors(Constants.DEFAULT_NUM_PROCESSORS);
			} else {
				bean.setNumProcessors(request.getNumProcessors());
			}
			
			/* set the userName in request bean */
			bean.setUserName(userName);
			
			/* set input file list */
			if(request.getInputFiles() != null) {
				for(InputFile file : request.getInputFiles()) {
					InputFileBean ipFileBean = new InputFileBean();
					ipFileBean.setFileName(file.getFileName());
					ipFileBean.setFileType(file.getFileType());
					
					bean.getInputFiles().add(ipFileBean);
				}
			}
			
		}
		return bean;
	}
	
	/**
	 * Gets the job response.
	 *
	 * @param bean the bean
	 * @return the job response
	 * @throws Exception the exception
	 */
	public static JobResponse getJobResponse(JobBean bean) throws Exception {
		JobResponse response = factory.createJobResponse();
		
		/* check if bean is valid */
		if(bean != null) {
			response.setJobId(bean.getJobId());
			response.setJobName(bean.getJobName());
			response.setUserName(bean.getUserName());
			response.setStatus(bean.getStatus());
			response.setElapsedTime(bean.getElapsedTime());
			response.setNumNodes(bean.getNumNodes());
			response.setNumProcessors(bean.getNumProcessors());
			response.setRequiredMemory(bean.getRequiredMemory());
			response.setRequiredTime(bean.getRequiredTime());
			response.setQueue(bean.getQueue());
		} else {
			throw new Exception("Empty JobBean received. Cannot construct the response object.");
		}
		return response;
	}
	
	/**
	 * Gets the user dao entity.
	 *
	 * @param userJAXB the user jaxb
	 * @return the user dao entity
	 * @throws Exception the exception
	 */
	public static edu.sga.apex.entity.User getUserDAOEntity(User userJAXB) throws Exception {
		edu.sga.apex.entity.User userEntity = new edu.sga.apex.entity.User();
		
		/* check if jaxb is valid */
		if(userJAXB != null) {
			//TODO: Currently setting userID as 1+num-users. Change this logic
			userEntity.setId(UserDAOUtil.getUserList().size() + 1);
			userEntity.setUsername(userJAXB.getUserName());
			userEntity.setPassword(userJAXB.getPassword());
			
			/* set USER role for user */
			Role role = UserDAOUtil.getRoleByRoleName(UserRoles.USER.getRole());
			userEntity.getRoles().add(role);
		} else {
			throw new Exception("Empty User JAXB received. Cannot construct the DAO entity.");
		}
		
		return userEntity;
	}
	
	/**
	 * Gets the experiment list response.
	 *
	 * @param experimentList the experiment list
	 * @return the experiment list response
	 * @throws Exception the exception
	 */
	public static ExperimentListResponse getExperimentListResponse(List<Experiment> experimentList) throws Exception {
		ExperimentListResponse expListResponse = factory.createExperimentListResponse();
		
		/* check if entity is valid */
		if(experimentList != null) {
			for(Experiment experiment : experimentList) {
				/* get experiment jaxb from dao */
				edu.sga.apex.rest.jaxb.Experiment experimentJAXB = getExperimentJAXB(experiment);
				
				/* add experiment jaxb to list response */
				expListResponse.getExperimentList().add(experimentJAXB);
			}
		} else {
			throw new Exception("Empty Experiment List DAO received. Cannot construct JAXB.");
		}
		return expListResponse;
	}
	
	/**
	 * Gets the experiment jaxb.
	 *
	 * @param experiment the experiment
	 * @return the experiment jaxb
	 * @throws Exception the exception
	 */
	public static edu.sga.apex.rest.jaxb.Experiment getExperimentJAXB(Experiment experiment) throws Exception {
		/* check valid experiment dao */
		if(experiment != null) {
			edu.sga.apex.rest.jaxb.Experiment experimentJAXB = factory.createExperiment();
			
			/* set basic jaxb parameters */
			experimentJAXB.setJobID(experiment.getJobId());
			experimentJAXB.setJobName(experiment.getJobName());
			experimentJAXB.setStatus(experiment.getStatus());
			experimentJAXB.setWallTime(experiment.getWallTime());
			experimentJAXB.setNumNodes(experiment.getNumOfNodes());
			experimentJAXB.setNumProcPerNode(experiment.getProcPerNode());
			
			/* set date parameters */
			if(experiment.getCreatedAt() != null && experiment.getUpdatedAt() != null) {
				experimentJAXB.setCreatedAt(APIUtil.getXMLGregorianCalendar(experiment.getCreatedAt()));
				experimentJAXB.setUpdatedAt(APIUtil.getXMLGregorianCalendar(experiment.getUpdatedAt()));
			}
			
			/* check if user entity exists */
			if(experiment.getUserName() != null) {
				experimentJAXB.setUserName(experiment.getUserName().getUsername());
			}
			
			/* construct the application jaxb from dao */
			if(experiment.getApplication() != null) {
				Application applicationJAXB = factory.createApplication();
				applicationJAXB.setAppID(experiment.getApplication().getAppId());
				applicationJAXB.setAppName(experiment.getApplication().getAppName());
				applicationJAXB.setScriptPath(experiment.getApplication().getScript_path());
				
				/* set the application jaxb */
				experimentJAXB.setApplication(applicationJAXB);
			}
			
			/* construct the machine jaxb from dao */
			if(experiment.getMachine() != null) {
				Machine machineJAXB = factory.createMachine();
				machineJAXB.setHostName(experiment.getMachine().getHostname());
				machineJAXB.setMachineID(experiment.getMachine().getMachineId());
				machineJAXB.setMachineName(experiment.getMachine().getMachineName());
				machineJAXB.setPortNumber(experiment.getMachine().getPortNum());
				machineJAXB.setWorkingDir(experiment.getMachine().getWorking_dir());
				
				/* set the machine jaxb */
				experimentJAXB.setMachine(machineJAXB);
			}
			
			return experimentJAXB;
		} else {
			throw new Exception("Empty Experiment DAO received. Cannot construct JAXB.");
		}
	}
	
	/**
	 * Gets the application list response.
	 *
	 * @param applicationList the application list
	 * @return the application list response
	 * @throws Exception the exception
	 */
	public static ApplicationListResponse getApplicationListResponse(List<edu.sga.apex.entity.Application> applicationList) throws Exception {
		ApplicationListResponse appListResponse = factory.createApplicationListResponse();
		
		/* check valid dao entity */
		if(applicationList != null) {
			for(edu.sga.apex.entity.Application application : applicationList) {
				/* construct application jaxb for each dao */
				Application applicationJAXB = factory.createApplication();
				applicationJAXB.setAppID(application.getAppId());
				applicationJAXB.setAppName(application.getAppName());
				applicationJAXB.setScriptPath(application.getScript_path());
				
				/* check valid application input */
				if(application.getInputList() != null && !application.getInputList().isEmpty()) {
					for(AppInput appInputDAO : application.getInputList()) {
						/* construct app input jaxb */
						ApplicationInput appInputJAXB = factory.createApplicationInput();
						appInputJAXB.setInput(appInputDAO.getInput());
						appInputJAXB.setDescription(appInputDAO.getDescription());
						
						/* add appInput jaxb to application jaxb */
						applicationJAXB.getAppInputs().add(appInputJAXB);
					}
				}
				
				/* add application jaxb to list response */
				appListResponse.getApplicationList().add(applicationJAXB);
			}
		} else {
			throw new Exception("Empty Application List DAO received. Cannot construct JAXB.");
		}
		
		return appListResponse;
	}
}
