SELECT Pname, COUNT(DISTINCT Fname) AS num, SUM(Hours) AS hrs
FROM EMPLOYEE, PROJECT, WORKS_ON, DEPARTMENT 
WHERE Ssn = Essn AND Pnumber=Pno AND Dnumber = Dnum 
GROUP BY Pname 
ORDER BY Dname,Pname;