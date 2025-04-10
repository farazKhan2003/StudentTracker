import * as React from 'react';
import { useState, useEffect } from 'react';
import { TextField, Container, Paper, Box, Button, ThemeProvider, Typography, Snackbar, IconButton, Dialog, DialogActions, DialogContent, DialogTitle } from '@mui/material';
import { createTheme, alpha, getContrastRatio } from '@mui/material/styles';
import CloseIcon from '@mui/icons-material/Close';

export default function AdminHome({ onAdminLogin }) {
  const paperStyle = { padding: '50px 20px', width: 600, margin: '20px auto' };
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [students, setStudents] = useState([]);
  const [open, setOpen] = React.useState(false);
  
  // For handling edit dialog
  const [openEditDialog, setOpenEditDialog] = useState(false);
  const [editStudent, setEditStudent] = useState({ id: '', name: '', email: '' });

  // Fetch student data
  const fetchStudents = () => {
    fetch("http://localhost:8080/student/getall")
      .then(result => result.json())
      .then(result => setStudents(result))
      .catch(error => console.error("Error fetching student data: ", error));
  };

  const handleClickSubmit = (e) => {
    e.preventDefault();
    const student = { name, email };
    fetch("http://localhost:8080/student/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(student),
    }).then(() => {
      setName('');
      setEmail('');
      fetchStudents();
    });
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleDelete = (id) => {
    const confirmed = window.confirm(`Are you sure you want to delete the student with id: ${id}?`);
    if (!confirmed) return;

    fetch(`http://localhost:8080/student/delete/${id}`, {
      method: 'DELETE',
    }).then(() => {
      fetchStudents();
      handleClick();
    });
  };

  const handleEdit = (student) => {
    setEditStudent({
      id: student.id,
      name: student.name,
      email: student.email,
    });
    setOpenEditDialog(true); // Open the dialog
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    const updatedStudent = {
      id: editStudent.id,
      name: editStudent.name,
      email: editStudent.email,
    };
    fetch(`http://localhost:8080/student/alter/${updatedStudent.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedStudent),
    })
      .then((response) => response.json())
      .then(() => {
        fetchStudents(); // Refresh the student list
        setOpenEditDialog(false); // Close the edit dialog
      })
      .catch((error) => {
        console.error('Error updating student data: ', error);
      });
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

  const redBase = '#d32f2f';
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
          <h1 style={{ color: "LightSlateGrey" }}><u>Add Student:</u></h1>
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
          {students.length === 0 ? (
            <p>No Student data found</p>
          ) : (
            students.map((student) => (
                <Paper elevation={6} style={{ margin: "10px", padding: "15px", textAlign: "left" }} key={student.id}>
                <div>
                  ID: {student.id}<br />
                  Name: {student.name}<br />
                  Email Address: {student.email}<br />
                </div>
                <div style={{ display: 'flex', justifyContent: 'flex-end', gap: '10px', marginTop: '10px' }}>
                  <Button variant="contained" color="red" onClick={() => handleDelete(student.id)}>
                    Delete
                  </Button>
                  <Button variant="contained" color="primary" onClick={() => handleEdit(student)}>
                    Edit
                  </Button>
                </div>
              </Paper>
            ))
          )}
        </Paper>

        <Dialog open={openEditDialog} onClose={() => setOpenEditDialog(false)} maxWidth="sm" fullWidth>
  <DialogTitle>Edit Student</DialogTitle>
  <DialogContent style={{ padding: '20px' }}>
    <TextField
      label="Name"
      variant="outlined"
      fullWidth
      value={editStudent.name}
      onChange={(e) => setEditStudent({...editStudent, name: e.target.value})}
      style={{ marginBottom: '20px' }}
    />
    <TextField
      label="Email"
      variant="outlined"
      fullWidth
      value={editStudent.email}
      onChange={(e) => setEditStudent({...editStudent, email: e.target.value})}
      inputProps={{ style: { fontSize: '16px' } }} // Adjust font size if needed
    />
  </DialogContent>
  <DialogActions>
    <Button onClick={() => setOpenEditDialog(false)} color="secondary">
      Cancel
    </Button>
    <Button onClick={handleUpdate} color="primary">
      Save
    </Button>
  </DialogActions>
</Dialog>

        <Snackbar
          open={open}
          autoHideDuration={6000}
          onClose={handleClose}
          message="Student Deleted"
          action={action}
        />
      </Container>
    </ThemeProvider>
  );
}
