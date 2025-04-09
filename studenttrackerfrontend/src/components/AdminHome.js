import * as React from 'react';
import { useState, useEffect} from 'react';
import { TextField, Container, Paper, Box, Button, ThemeProvider, Typography, Snackbar, IconButton } from '@mui/material';
import { createTheme, alpha, getContrastRatio } from '@mui/material/styles';
import CloseIcon from '@mui/icons-material/Close';


export default function AdminHome( { onAdminLogin }) {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [students, setStudents] = useState([]);
  const [open, setOpen] = React.useState(false);
  

  const fetchStudents = () => {
    fetch("http://localhost:8080/student/getall")
    .then(result => result.json())
    .then(result => setStudents(result))
    .catch(error => console.error("Error fetching student data: ", error));
  };

  const handleClickSubmit =(e) => {
    e.preventDefault()
    const student={name, email}
    fetch("http://localhost:8080/student/add",{
      method:"POST",
      headers:{"Content-Type":"application/json"},
      body:JSON.stringify(student)
      
    }).then(()=>{
      setName('');
      setEmail('');
      fetchStudents();
    });
  };
  

  React.useEffect(()=> {
    fetch("http://localhost:8080/student/getall")
    .then(response=>response.json())
    .then((result) => {
      setStudents(result);
    }
  ) .catch((error) => {
    console.error("Error fetching student data", error);
  });
  },[]);

  const handleDelete = (id) => {
    const confirmed = window.confirm(`Are you sure you want to delete the student with id: ${id}?`);

    if (!confirmed) return;

    fetch(`http://localhost:8080/student/delete/${id}`, {
        method: 'DELETE',
    }).then(() => {
         fetchStudents();
         handleClick();
    })
    };
  
    const handleClick = () => {
        setOpen(true);
      };
    
      const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
          return;
        }
    
        setOpen(false);
      };

      const action = (
        <React.Fragment>
          <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={handleClose}
          >
            <CloseIcon fontSize="small" />
          </IconButton>
        </React.Fragment>
      );

  const redBase = '#d32f2f'
  const redMain = alpha(redBase, 0.7);
  const theme = createTheme({
    palette: {
      red: {
        main: redMain,
        light: alpha(redBase, 0.5),
        dark: alpha(redBase, 0.9),
        contrastText: getContrastRatio(redMain, '#fff') > 4.5 ? '#fff' : '#111',
      },
    },
  });
  
  

  return (
    <ThemeProvider theme={theme}>
    <Container>
      <Paper elevation={4} style={paperStyle}>
          <h1 style={{color:"LightSlateGrey"}}><u>Add Student:</u></h1>
        <Box
          component="form"
          sx={{
            '& > :not(style)': { m: 1 },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField
            id="student-name"
            label="Name" 
            variant="outlined"
            fullWidth
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          <TextField
            id="student-email"
            label="Email"
            variant="outlined"
            fullWidth
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <Button variant="contained" color="red" onClick={handleClickSubmit}>
  Submit 
</Button>
        </Box>
      </Paper>
      <Box display="flex" justifyContent="center" alignItems="center">
      <Typography variant="h4">Students</Typography>
    </Box>
      <Paper elevation={3} style={paperStyle}>
          {
             students.length === 0 ? (
              <p>No Student data found</p>
            ) : (
          students.map((student) => ( 
            <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key ={student.id}
            >
            <div>
              ID: {student.id}<br/>
              Name: {student.name}<br/>
              Email Address: {student.email}<br/> 
            </div>
            <Button variant="contained" color="red" onClick={() => {
                handleDelete(student.id);
                 }}>
                Delete
                </Button>
                <Snackbar 
                    open={open}
                    autoHideDuration={6000}
                    onClose={handleClose}
                    message="Student Deleted"
                    action={action}
                />
            </Paper>)))}
            
            
          </Paper>
    </Container>
    </ThemeProvider>
          );
}
