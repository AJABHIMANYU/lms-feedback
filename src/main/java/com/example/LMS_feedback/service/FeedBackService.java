package com.example.LMS_feedback.service;

import com.example.LMS_feedback.model.Feedback;
import com.example.LMS_feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackService {

    @Service

    public class FeedbackService {

        @Autowired
        private FeedbackRepository repo;

        public List<Feedback> getAllFeedback() {
            return repo.findAll();
        }

        public Feedback getFeedbackById(Long feedbackId) {
            Optional<Feedback> op = repo.findByFeedbackId(feedbackId);
            if(op.isPresent()){
                return op.get();
            }
            else{
                throw new RuntimeException("Feedback not found with id " + feedbackId);
            }
        }
        public List<Feedback> createbatchFeedback(List<Feedback> feedbacks) {
            return repo.saveAll(feedbacks);
        }

        public Feedback createFeedback(Feedback feedback) {
            return repo.save(feedback);
        }

        public Feedback updateFeedback(Long feedbackId, Feedback feedbackDetails) {
            Optional<Feedback> feedbackOptional = repo.findByFeedbackId(feedbackId);

            if (feedbackOptional.isPresent()) {
                Feedback feedback = feedbackOptional.get();
                feedback.setUserCourseId(feedbackDetails.getUserCourseId());
                feedback.setRating(feedbackDetails.getRating());
                feedback.setComments(feedbackDetails.getComments());
                feedback.setFeedbackDate(feedbackDetails.getFeedbackDate());
                return repo.save(feedback);
            } else {
                throw new RuntimeException("Feedback not found with id " + feedbackId);
            }
        }

        public void deleteFeedback(Long feedbackId) {
            repo.deleteById(feedbackId);
        }
    }

}