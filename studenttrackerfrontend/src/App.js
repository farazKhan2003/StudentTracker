import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Student from './components/Student';
import Login from './components/Login';
import Appbar from './components/Appbar';

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <Router>
      <Appbar />
      <Routes>
        <Route path="/" element={<Student/>} />
        <Route path="/login" element={<Login onLogin={setIsLoggedIn} />} />
      </Routes>
    </Router>
    
  );
}
