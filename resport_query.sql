SELECT
	p.FirstName,
	j.JobTitle,
    pa.idpersonactivities

    FROM 
	hris_hiring.job_qualification j, 
    hris_hiring.person p,
    hris_hiring.nsb_person_activities pa
    RIGHT OUTER JOIN hris_hiring.nsb_activities a
    ON a.nsb_entity_activities = pa.idpersonactivities
    
    where
    p.idPerson = j.person_id AND
    pa.person_idPerson = p.idPerson
    

 
    