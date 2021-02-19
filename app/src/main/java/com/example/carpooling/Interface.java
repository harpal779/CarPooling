<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#8AFF9800"
    tools:context=".Interface">

    <Button
        android:id="@+id/button2"
        android:layout_width="253dp"
        android:layout_height="67dp"
        android:layout_marginStart="81dp"

        android:layout_marginLeft="81dp"
        android:layout_marginTop="26dp"
        android:background="@drawable/border"
        android:fontFamily="@font/fredoka_one"
        android:onClick="Home"
        android:text=" Available Rides"
        android:textColor="#FFFFFF"
        android:textSize="20sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/logo_car"
        tools:ignore="OnClick" />

    <Button
        android:id="@+id/button4"
        android:layout_width="183dp"
        android:layout_height="72dp"
        android:layout_marginStart="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="68dp"
        android:background="@drawable/border"
        android:fontFamily="@font/fredoka_one"
        android:text="LogOut"
        android:textColor="#FFFFFF"
        android:textSize="20sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button21"
        tools:ignore="MissingConstraints" />

    <Button
        android:id="@+id/button22"
        android:layout_width="183dp"
        android:layout_height="72dp"
        android:layout_marginStart="13dp"
        android:layout_marginLeft="13dp"
        android:layout_marginTop="68dp"
        android:background="@drawable/border"
        android:fontFamily="@font/fredoka_one"
        android:text="Edit Profile"
        android:onClick="edit"
        android:textColor="#FFFFFF"
        android:textSize="20sp"
        app:layout_constraintStart_toEndOf="@+id/button4"
        app:layout_constraintTop_toBottomOf="@+id/button21"
        tools:ignore="MissingConstraints,OnClick" />

    <ImageView
        android:id="@+id/logo_car"
        android:layout_width="348dp"
        android:layout_height="210dp"
        android:layout_marginStart="37dp"
        android:layout_marginLeft="37dp"
        android:layout_marginTop="49dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/carlogo"
        tools:ignore="MissingConstraints" />

    <Button
        android:id="@+id/button21"
        android:layout_width="257dp"
        android:layout_height="67dp"
        android:layout_marginStart="81dp"

        android:layout_marginLeft="81dp"
        android:layout_marginTop="17dp"
        android:background="@drawable/border"
        android:fontFamily="@font/fredoka_one"
        android:onClick="Home"
        android:text="Book Ride"
        android:textColor="#FFFFFF"
        android:textSize="20sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button2"
        tools:ignore="OnClick" />

    <Button
        android:id="@+id/button23"
        android:layout_width="145dp"
        android:layout_height="48dp"
        android:layout_marginStart="21dp"
        android:layout_marginLeft="21dp"
        android:layout_marginTop="13dp"
        android:background="@drawable/border"
        android:fontFamily="@font/fredoka_one"
        android:text="Need Ride"
        android:textColor="#FFFFFF"
        android:textSize="20sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button4" />

</androidx.constraintlayout.widget.ConstraintLayout>
