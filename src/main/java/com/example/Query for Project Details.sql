SELECT CONCAT(Fname,' ',Minit,'. ',Lname) AS Name, Dname, Plocation, Pname, Hours, (Salary*(Hours/40)) AS FC, Address
FROM EMPLOYEE,DEPARTMENT, PROJECT, WORKS_ON
WHERE Dnum = Dnumber AND Pno = Pnumber AND Essn = Ssn AND Hours IS NOT NULL 
ORDER BY Dname,Pname, Name;
