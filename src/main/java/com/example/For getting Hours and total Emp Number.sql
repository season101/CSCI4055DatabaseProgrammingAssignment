SELECT Pname, COUNT(DISTINCT Fname) AS num, SUM(Hours) AS hrs, SUM(Salary*(Hours/40))
FROM EMPLOYEE, PROJECT, WORKS_ON, DEPARTMENT 
WHERE Ssn = Essn AND Pnumber=Pno AND Dnumber = Dnum AND Hours IS NOT NULL
GROUP BY Pname 
ORDER BY Dname,Pname;