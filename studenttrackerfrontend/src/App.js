import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Student from './components/Student';
import Login from './components/Login';
import AdminHome from './components/AdminHome';
import Appbar from './components/Appbar';

export default function App() {
   const [isAdminLoggedIn, setIsAdminLoggedIn] = useState(false);

  return (
    <Router>
      <Appbar isAdminLoggedIn={isAdminLoggedIn} setIsAdminLoggedIn={setIsAdminLoggedIn} />
      <Routes>
        <Route path="/" element={<Student/>} />
        <Route path="/login" element={<Login onLogin={() => setIsAdminLoggedIn(true)} />} />
        <Route path="/admin" element={<AdminHome />} />
      </Routes>
    </Router>
    
  );
}
