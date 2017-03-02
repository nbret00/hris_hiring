SELECT
	p.idPerson,
	p.FirstName,
    j.JobTitle,
    pa.idpersonactivities,
	a.description,
    a.nsb_activity_tp,
    a.nsb_activity_status_tp,
    c.CompanyName,
    jo.Title
    FROM 
    hris_hiring.nsb_person_activities pa
    RIGHT OUTER JOIN hris_hiring.nsb_activities a
    ON a.nsb_entity_activities = pa.idpersonactivities,
    hris_hiring.person p
	LEFT OUTER JOIN	hris_hiring.job_qualification j
    ON j.person_id = p.idPerson
    LEFT OUTER JOIN	hris_hiring.endorsement e
	ON p.idPerson = e.person_idPerson
    LEFT OUTER JOIN hris_hiring.company c
    ON e.company_idclient = c.idclient
    LEFT OUTER JOIN hris_hiring.job jo
    ON e.job_idjobpk = jo.idjobpk
    WHERE 
    
    p.idPerson = pa.person_idPerson AND
    a.nsb_activity_tp = 1
    AND a.nsb_activity_status_tp = 1
 
    