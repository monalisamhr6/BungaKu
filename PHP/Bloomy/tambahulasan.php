<?php

include 'koneksi.php';

// Assuming you are receiving these values through POST, adjust as needed
$review = $_POST['review'];
$rating = $_POST['rating'];

// Use prepared statement to prevent SQL injection
$st_insert = $koneksi->prepare("INSERT INTO ulasan (review, rating) VALUES (?, ?)");
$st_insert->bind_param("sd", $review, $rating); // Assuming 's' for string and 'd' for double, adjust based on your column types

// Execute the prepared statement
if ($st_insert->execute()) {
    $response['success'] = true;
    $response['message'] = "Review added successfully.";
} else {
    $response['success'] = false;
    $response['message'] = "Failed to add review.";
}

// Close the prepared statement and database connection
$st_insert->close();
$koneksi->close();

// Send the JSON response back to the Android app
echo json_encode($response);

?>
